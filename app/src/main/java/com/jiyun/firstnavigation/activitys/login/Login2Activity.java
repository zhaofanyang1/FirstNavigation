package com.jiyun.firstnavigation.activitys.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.firstnavigation.MainActivity;
import com.jiyun.firstnavigation.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login2Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.id_shut)
    ImageView idShut;
    @BindView(R.id.id_bigheads)
    ImageView idBigheads;
    @BindView(R.id.picture)
    ImageView picture;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.wancheng)
    ImageView wancheng;
    @BindView(R.id.tiaoguo)
    TextView tiaoguo;
    @BindView(R.id.text_shangchuan)
    TextView shangchuan;

    private Uri uriForFile;

    private AlertDialog dialog;

    private static final int TAKE_PHOTO = 1;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 4;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;


    //调用照相机返回图片文件
    private File tempFile;
    //最后显示的图片文件
    private String mFile;
    private TextView mAlbum;
    private TextView mCamera1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        idShut.setOnClickListener(this);
        idBigheads.setOnClickListener(this);
        wancheng.setOnClickListener(this);
        tiaoguo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_shut:
                initTiaozhuan();
                break;
            case R.id.id_bigheads:
                uphead();
                break;
            case R.id.text_shangchuan:
                shangchuan();
                break;
            case R.id.wancheng:
                wangcheng();
                break;
            case R.id.tiaoguo:
                tiaoguo();
                break;
        }
    }

    private void shangchuan() {

    }

   /* public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }*/

    //完成；
    private void wangcheng() {
        if (name.getText().toString() != null) {
            startActivity(new Intent(Login2Activity.this, MainActivity.class));
        } else if (name.getText().toString() == null) {
            Toast.makeText(this, "昵称不可为空，可以重复，请保持昵称在40个字符/20个汉字，不可输入表情！", Toast.LENGTH_SHORT).show();
        }
    }


    //上传头像
    private void uphead() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop, null);
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOutsideTouchable(true);
        View photograph = view.findViewById(R.id.photograph);
        View photo = view.findViewById(R.id.photo);
        View cancel = view.findViewById(R.id.cancel);
        //调用系统相机
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建File对象,用于存储拍照后的图片
                File file = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    uriForFile = FileProvider.getUriForFile(Login2Activity.this, "", file);
                } else {
                    uriForFile = Uri.fromFile(file);
                }
                //启动相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        //调用系统相册
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPicFromAlbm();
            }
        });

        //取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
    //相册
    private void getPicFromAlbm(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }


    //回传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //启动相机程序
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriForFile));
                        idBigheads.setVisibility(View.GONE);
                        picture.setVisibility(View.VISIBLE);
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 4:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //usersUploadHeadImagePresenter.getUsersUploadHeadImagePresenter("e7d9914be4214263ad8e2a3e88e72263",image+"");
                    //设置到ImageView上
                    idBigheads.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);

                }
                break;
            default:
                break;
        }
    }

    private void initTiaozhuan() {
        startActivity(new Intent(this, LoginActivity.class));
    }
    private void tiaoguo() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否确认使用默认的用户名:巴啦啦小魔仙");
        builder.setNegativeButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toast("去设置");
            }

            private void toast(String 去设置) {
                Toast.makeText(Login2Activity.this, "去设置", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toast("使用");
            }

            private void toast(String 使用) {
                name.setText("巴啦啦小魔仙");
                Toast.makeText(Login2Activity.this, "巴啦啦小魔仙", Toast.LENGTH_SHORT).show();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }


    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
