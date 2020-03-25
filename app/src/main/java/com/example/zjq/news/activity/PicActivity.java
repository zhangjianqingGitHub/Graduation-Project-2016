package com.example.zjq.news.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telecom.Call;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.zjq.news.R;
import com.example.zjq.news.bean.Avatar;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.PhotoUtils;
import com.example.zjq.news.utils.SharepUtils;
import com.example.zjq.news.utils.ToastUtil;
import com.example.zjq.news.utils.UtilImags;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class PicActivity extends Activity {
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private ImageView mFace;
    private RelativeLayout pic_rl;
    private Uri uri;
    private Bitmap bitmap;
    private Button Button1, btn_pai;
    private boolean off = false;
    private boolean is_CAMERA = true;

    /* 20171012*/
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    //Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg"
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_pic);

        initView();
        listener();


    }

    private void listener() {

        MyListener listener = new MyListener();
        btn_pai.setOnClickListener(listener);

    }

    private void initView() {

        Button1 = (Button) this.findViewById(R.id.bt_me_up);
        pic_rl = (RelativeLayout) this.findViewById(R.id.pic_rl);

        btn_pai = findViewById(R.id.btn_pai);

    }


//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        getWindow().setAttributes(lp);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_pai:
                    //点击拍照
                    autoObtainCameraPermission();
                    break;
            }
        }
    }

    public void uploadFile(File file) {

        if (!file.exists()) {
            return;
        }

        String url = Constants.PIC_URL;

        RequestParams params = new RequestParams(url);

        params.addBodyParameter("uid", SharepUtils.getUserUSER_ID(PicActivity.this));
        params.addBodyParameter("key", SharepUtils.getUserUSER_KEY(PicActivity.this));

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    Gson gson = new Gson();
                    Avatar packlist = gson.fromJson(result, Avatar.class);

                    if (packlist.getCode() == 2000) {
                        SharepUtils.setUSER_AVATAR(PicActivity.this, packlist.getData().getAvatar());

                    }
                    Intent intent = new Intent();
                    intent.putExtra("type", "pic");
                    setResult(1, intent);

                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                } catch (Exception e) {
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }


    /*
     * 上传图片
     */
    public void upload(View view) {
        if (off) {
            try {
                String name = DateFormat.format("yyyyMMdd_hhmmss",
                        Calendar.getInstance(Locale.CHINA)) + ".jpg";

                FileOutputStream fout = null;
                String filename = null;

                try {
                    filename = UtilImags.SHOWFILEURL(PicActivity.this) + "/" + name;
                } catch (IOException e) {
                }

                try {
                    fout = new FileOutputStream(filename);

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fout);

                } catch (Exception e) {
                    ToastUtil.showShort(PicActivity.this, "上传失败");

                } finally {
                    try {
                        fout.flush();
                        fout.close();
                    } catch (IOException e) {
                        ToastUtil.showShort(PicActivity.this, "上传失败");

                    }
                }
                uploadFile(new File(filename));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            finish();
        }

    }

    /**20171212 start*/
    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ToastUtil.showShort(this, "您已经拒绝过一次");

            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(PicActivity.this, "com.youjing.yingyudiandu.FileProvider", fileUri);
                }
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtil.showShort(this, "设备没有SD卡！");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            //通过FileProvider创建一个content类型的Uri
                            imageUri = FileProvider.getUriForFile(PicActivity.this, "com.youjing.yingyudiandu.FileProvider", fileUri);
                        }

                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtil.showShort(this, "设备没有SD卡！");
                    }
                } else {

                    ToastUtil.showShort(this, "请允许打开相机！！");
                }
                break;


            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    ToastUtil.showShort(PicActivity.this, "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }

    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(this, "com.youjing.yingyudiandu.FileProvider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtil.showShort(this, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        off = true;
//                        Button1.setText("确定");
//                        Button1.setTextColor(this.getResources().getColor(R.color.white));
//                        Button1.setBackgroundResource(R.drawable.bg_me_task_bt);
                        pic_rl.setVisibility(View.GONE);

                        if (off) {
                            try {
                                String name = DateFormat.format("yyyyMMdd_hhmmss",
                                        Calendar.getInstance(Locale.CHINA)) + ".jpg";

                                FileOutputStream fout = null;
                                String filename = null;

                                try {
                                    filename = UtilImags.SHOWFILEURL(PicActivity.this) + "/" + name;
                                } catch (IOException e) {
                                }

                                try {
                                    fout = new FileOutputStream(filename);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fout);
                                } catch (Exception e) {
                                    ToastUtil.showShort(PicActivity.this, "上传失败");

                                } finally {
                                    try {
                                        fout.flush();
                                        fout.close();
                                    } catch (IOException e) {
                                        ToastUtil.showShort(PicActivity.this, "上传失败");
                                    }
                                }
                                uploadFile(new File(filename));

                            } catch (Exception e) {

                                Log.e("zjq-e", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                default:
            }
        }
    }

    /**
     * 20171212 end
     */
    /*
     * 从相册获取
     */
    public void gallery(View view) {
        autoObtainStoragePermission();

    }


    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    1);
            is_CAMERA = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
