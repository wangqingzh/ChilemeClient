package com.wangqing.chilemecilent.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;

import com.wangqing.chilemecilent.R;

public class InputTextDialog extends AppCompatDialog {

    public interface OnTextSendListener {
        void onTextSend(String text);
    }


    private Context context;

    private EditText editComment;
    private ImageButton buttonSend;
    private InputMethodManager inputMethodManager;

    private OnTextSendListener onTextSendListener;


    private TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() > 0) {
                buttonSend.setEnabled(true);
                buttonSend.setClickable(true);
                buttonSend.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
            } else {
                buttonSend.setEnabled(false);
                buttonSend.setColorFilter(ContextCompat.getColor(context, R.color.iconCover));
            }
        }
    };

    public InputTextDialog(Context context) {
        super(context, R.style.BottomDialog);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_comment);

        setCanceledOnTouchOutside(true);

        WindowManager windowManager = getWindow().getWindowManager();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.BOTTOM;

        getWindow().setAttributes(layoutParams);

        editComment = findViewById(R.id.editComment);
        buttonSend = findViewById(R.id.buttonSend);


        //设置按钮颜色
        buttonSend.setColorFilter(ContextCompat.getColor(context, R.color.iconCover));



        editComment.addTextChangedListener(mTextWatcher);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editComment.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {
                    onTextSendListener.onTextSend(text);
                    editComment.setText("");
                    dismiss();
                }
            }
        });




        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setSoftKeyboard();

    }



    @Override
    public void dismiss() {
        super.dismiss();
    }


    @Override
    public void show() {
        super.show();
    }


    private void setSoftKeyboard() {
        editComment.setFocusable(true);
        editComment.setFocusableInTouchMode(true);
        editComment.requestFocus();

        // 为 commentEditText 设置监听器，在 DialogFragment 绘制完后立即呼出软键盘，呼出成功后即注销
        editComment.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.showSoftInput(editComment, 0)) {
                    editComment.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }



    public void setOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.onTextSendListener = onTextSendListener;
    }

    public void setHit(String hit) {
        editComment.setHint(hit);
    }

    public void clearText(){
        editComment.setText("");
    }
}
