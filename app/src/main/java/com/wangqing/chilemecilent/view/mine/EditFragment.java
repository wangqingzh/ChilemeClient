package com.wangqing.chilemecilent.view.mine;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentEditBinding;
import com.wangqing.chilemecilent.viewmodel.bottomnavigation.MineViewModel;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().toString();

    private FragmentEditBinding binding;
    private MineViewModel mineViewModel;

    private static final int TAKE_PHOTO = 0; // 拍照
    private static final int SELECT_PHOTO = 1;  // 选择照片

    private static final int TAKE_PHOTO_PERMISSION_REQUEST_CODE = 0; // 拍照权限请求返回码
    private static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1; // 读取存储卡内容权限处理返回码


    private static final int TAKE_PHOTO_REQUEST_CODE = 3; // 拍照返回的requestCode
    private static final int SELECT_FROM_ALBUM_REQUEST_CODE = 4; // 选取图片返回的requestCode
    private static final int CROP_PHOTO_REQUEST_CODE = 5; // 裁剪图片返回的requestCode

    private Uri photoUri = null; // 访问拍照的uri
    private Uri photoOutputUri = null; // 图片最终的输出文件的Uri

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false);
        mineViewModel = new ViewModelProvider(requireActivity()).get(MineViewModel.class);
        binding.setData(mineViewModel);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请读写内存卡内容的权限
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_SDCARD_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        binding.editNikename.setOnClickListener(this);
        binding.editAvatar.setOnClickListener(this);
        binding.editIntro.setOnClickListener(this);
        binding.editCover.setOnClickListener(this);
    }

    /**
     * 处理点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_nikename:
                editNikeName();
                break;
            case R.id.edit_intro:
                editIntro();
                break;
            case R.id.edit_avatar:
                editAvatar();
                break;
            case R.id.edit_cover:
                editCover();
                break;
            default:
                break;
        }
    }

    private void editCover() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("选择照片");
        builder.setItems(R.array.photo_select_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {

                }
            }
        });
        builder.show();
    }

    private void editAvatar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("选择照片");
        builder.setItems(R.array.photo_select_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PHOTO:
                        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO_PERMISSION_REQUEST_CODE);
                        } else {
                            startCamera();
                        }
                        break;
                    case SELECT_PHOTO:
                        selectFromAlbum();
                        break;
                }
            }
        });
        builder.show();
    }

    private void editIntro() {
        final EditText et = new EditText(requireContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("请输入简介");
        builder.setView(et);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void editNikeName() {
        final EditText et = new EditText(requireContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("请输入昵称");
        builder.setView(et);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    /**
     * 启动相机
     */
    private void startCamera() {
        // 将拍照得到的照片存储在缓存目录
        File file = new File(requireActivity().getExternalCacheDir(), "image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过 文件内容提供器 将拍来的照片访问uri暴露给其他应用
        photoUri = FileProvider.getUriForFile(requireContext(), "com.wangqing.fileProvider", file);

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); //设置拍照所得照片的输出目录
        startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST_CODE);
    }


    private void selectFromAlbum(){
        // 打开系统图库
        Intent selectFromAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // 设置数据类型为图片类型
        selectFromAlbumIntent.setType("image/*");
        startActivityForResult(selectFromAlbumIntent, SELECT_FROM_ALBUM_REQUEST_CODE);
    }

    /**
     * 裁剪图片
     * @param inputUri
     */
    public void cropPhoto(Uri inputUri) {
        // 调用系统裁剪图片的action
        Intent cropPhotoIntent = new Intent("com.android.camera.action.CROP");
        // 设置数据 Uri 和 类型
        cropPhotoIntent.setDataAndType(inputUri, "image/*");
        // 授权应用读取 Uri
        cropPhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 设置图片的最终输出目录
        cropPhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                photoOutputUri = Uri.parse("file:////sdcard/image_output.jpg"));
        startActivityForResult(cropPhotoIntent, CROP_PHOTO_REQUEST_CODE);
    }


    /**
     * 在这里进行用户授权结果处理
     *
     * @param requestCode  权限要求码，即我们申请权限时传入的常量
     * @param permissions  保存权限名称的 String 数组，可以同时申请一个以上的权限
     * @param grantResults 每一个申请的权限的用户处理结果数组(是否授权)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case TAKE_PHOTO_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "授权成功，即将打开相机！", Toast.LENGTH_SHORT).show();
                    startCamera();
                } else {
                    Toast.makeText(requireContext(), "请授予相机权限！", Toast.LENGTH_SHORT).show();
                }
                break;
            case WRITE_SDCARD_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(requireContext(), "读写内存卡内容权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * 通过这个 activity 启动的其他 Activity 返回的结果在这个方法进行处理
     * 我们在这里对拍照、相册选择图片、裁剪图片的返回结果进行处理
     *
     * @param requestCode 返回码，用于确定是哪个 Activity 返回的数据
     * @param resultCode  返回结果，一般如果操作成功返回的是 RESULT_OK
     * @param data        返回对应 activity 返回的数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO_REQUEST_CODE:
                    cropPhoto(photoUri);
                    break;
                case SELECT_FROM_ALBUM_REQUEST_CODE:
                    cropPhoto(data.getData());
                    break;
                case CROP_PHOTO_REQUEST_CODE:
                    File file = new File(photoOutputUri.getPath());
                    if (file.exists()) {
                        // 读取文件时需要 读取sd卡 权限
                        Bitmap bitmap = BitmapFactory.decodeFile(photoOutputUri.getPath());
                        binding.avatar.setImageBitmap(bitmap);
                    } else {
                        Toast.makeText(requireContext(), "找不到照片", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
