package com.wangqing.chilemecilent.view.foodrec;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentFoodRecPostBinding;
import com.wangqing.chilemecilent.viewmodel.foodRec.FoodRecPostViewModel;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodRecPostFragment extends Fragment implements AdapterView.OnItemSelectedListener,
        RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    private final String TAG = this.getClass().toString();

    private FragmentFoodRecPostBinding binding;
    private FoodRecPostViewModel foodRecPostViewModel;


    private static final int TAKE_PHOTO = 0; // 拍照
    private static final int SELECT_PHOTO = 1;  // 选择照片

    private static final int TAKE_PHOTO_PERMISSION_REQUEST_CODE = 0; // 拍照权限请求返回码
    private static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1; // 读取存储卡内容权限处理返回码


    private static final int TAKE_PHOTO_REQUEST_CODE = 3; // 拍照返回的requestCode
    private static final int SELECT_FROM_ALBUM_REQUEST_CODE = 4; // 选取图片返回的requestCode

    private Uri photoUri = null; // 访问拍照的uri

    public FoodRecPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        foodRecPostViewModel = new ViewModelProvider(this).get(FoodRecPostViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_rec_post, container, false);



        binding.setLifecycleOwner(requireActivity());
        binding.setData(foodRecPostViewModel);

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    /**
     * 初始化界面
     */
    private void initView() {
        // spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.post_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPartition.setAdapter(adapter);
        binding.spinnerPartition.setOnItemSelectedListener(this);
        // ratingBar
        binding.ratingBarRecommend.setOnRatingBarChangeListener(this);

        binding.buttonSelectImage.setOnClickListener(this);
        binding.buttonPost.setOnClickListener(this);
    }

    /**
     * 下拉框 spinner 回调
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        foodRecPostViewModel.getClassifyId().setValue(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * ratingBar 变化回调
     *
     * @param ratingBar
     * @param rating
     * @param fromUser
     */
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        binding.scoreView.setText(String.valueOf(rating));
        foodRecPostViewModel.getRecommendScore().setValue(rating);
    }

    /**
     * 按钮点击 回调
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSelectImage: // 选择图片
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("选择照片");
                builder.setItems(R.array.photo_select_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case TAKE_PHOTO:
                                getPermissionForCamera();
                                break;
                            case SELECT_PHOTO:
                                getPermissionForAlbum();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.buttonPost: // 发布 先传递信息 图片再发
                foodRecPostViewModel.addPost();
                break;
        }
    }

    /**
     * 为相机获得权限
     */
    private void getPermissionForCamera() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, TAKE_PHOTO_PERMISSION_REQUEST_CODE);
        } else {
            startCamera();
        }
    }

    /**
     * 为访问相册获取权限
     */
    private void getPermissionForAlbum() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请读写内存卡内容的权限
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_SDCARD_PERMISSION_REQUEST_CODE);
        } else {
            selectFromAlbum();
        }
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


    private void selectFromAlbum() {
        // 打开系统图库
        Intent selectFromAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // 设置数据类型为图片类型
        selectFromAlbumIntent.setType("image/*");
        startActivityForResult(selectFromAlbumIntent, SELECT_FROM_ALBUM_REQUEST_CODE);
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
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "授权成功，即将打开相机！", Toast.LENGTH_SHORT).show();
                    startCamera();
                } else {
                    Toast.makeText(requireContext(), "请到设置中授予相机与外部存储权限！", Toast.LENGTH_SHORT).show();
                }
                break;
            case WRITE_SDCARD_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "授权成功，即将打开相册！", Toast.LENGTH_SHORT).show();
                    selectFromAlbum();
                } else {
                    Toast.makeText(requireContext(), "请到设置中授予外部存储权限！", Toast.LENGTH_SHORT).show();
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
                    handlePhoto(true);
                    break;
                case SELECT_FROM_ALBUM_REQUEST_CODE:
                    photoUri = data.getData();
                    handlePhoto(false);
                    break;
            }
        }
    }

    /**
     * 对头像进行处理
     */
    private void handlePhoto(boolean flag) {
        File file = null;
        if (flag){   // 照相 图片存在了本app的外部缓存中
            file = new File(requireActivity().getExternalCacheDir(), "image.jpg");
            if (file.exists()) {
                // 读取文件时需要 读取sd卡 权限
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                binding.selectImage.setImageBitmap(bitmap);
            } else {
                Toast.makeText(requireContext(), "文件未找到！", Toast.LENGTH_SHORT).show();
            }

        }else {  // 从图库选择图片 需进行解析
            file = new File(getFilePath(photoUri));
            if (file.exists()) {
                // 读取文件时需要 读取sd卡 权限
                Bitmap bitmap = BitmapFactory.decodeFile(getFilePath(photoUri));
                binding.selectImage.setImageBitmap(bitmap);
            } else {
                Toast.makeText(requireContext(), "文件未找到！", Toast.LENGTH_SHORT).show();
            }
        }

        foodRecPostViewModel.getImage().setValue(file);

    }


    /**
     * 根据返回的uri 获取真实的图片路径
     * @param uri
     * @return
     */
    private String getFilePath(Uri uri) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(requireContext(), uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        return imagePath;
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = requireActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}
