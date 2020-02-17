package com.wangqing.chilemecilent.view.mine;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.wangqing.chilemecilent.MainActivity;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentEditBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment implements View.OnClickListener {

    private FragmentEditBinding binding;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    private void editNikeName(){
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
}
