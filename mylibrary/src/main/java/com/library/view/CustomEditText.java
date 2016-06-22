package com.library.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.library.R;
import com.library.utils.DensityUtil;

/**
 * Description 包含删除按钮，密码明暗切换
 * 2016/5/17.
 */
public class CustomEditText extends LinearLayout {

    private EditText mEditText;
    private ImageView imgClear;
    private ImageView imgCode;
    private Context mContext;
    private boolean isVisibility = false;

    public CustomEditText(Context context) {
        this(context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_edittext, this);
        mContext = context;
        mEditText = (EditText) findViewById(R.id.edit_text);
        imgClear = (ImageView) findViewById(R.id.btn_clear);
        imgCode = (ImageView) findViewById(R.id.btn_code);
        bindEvents();
    }

    private void bindEvents() {
        imgClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });

        imgCode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisibility = !isVisibility;
                setEditTextVisibility();
                if (mEditText.hasFocus()) {
                    imgClear.setVisibility(View.VISIBLE);
                } else {
                    imgClear.setVisibility(View.GONE);
                }
                mEditText.setSelection(mEditText.getText().length());
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imgClear.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int length = mEditText.getText().length();
                imgClear.setVisibility((hasFocus && length != 0) ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setEditTextVisibility() {
        if (isVisibility) {
            // 显示密码
            imgCode.setImageResource(R.drawable.code_open);
            mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            // 隐藏密码
            imgCode.setImageResource(R.drawable.code_close);
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    /**
     * 设置是否显示文字
     *
     * @param isVisible true/false
     */
    public CustomEditText setTextVisibility(boolean isVisible) {
        isVisibility = isVisible;
        setEditTextVisibility();
        return this;
    }

    /**
     * 设置明暗码开关是否可见
     *
     * @param visible View.GONE
     */
    public CustomEditText setBtnCodeVisibility(int visible) {
        imgCode.setVisibility(visible);
        return this;
    }

    /**
     * 设置输入框图标
     *
     * @param drawableId 资源id
     */
    public CustomEditText setDrawableLeft(int drawableId) {
        Drawable drawable = mContext.getResources().getDrawable(drawableId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mEditText.setCompoundDrawables(drawable, null, null, null);
            mEditText.setCompoundDrawablePadding(DensityUtil.dp2px(mContext, 10));
        }
        return this;
    }

    /**
     * 设置输入框提示文字
     *
     * @param stringId 资源id
     */
    public CustomEditText setHint(int stringId) {
        mEditText.setHint(mContext.getString(stringId));
        return this;
    }

    /**
     * 设置输入框提示字体颜色
     *
     * @param colorId 资源id
     */
    public CustomEditText setHintColor(int colorId) {
        mEditText.setHintTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置输入框字体颜色
     *
     * @param colorId 资源id
     */
    public CustomEditText setTextColor(int colorId) {
        mEditText.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置输入框字体大小
     *
     * @param size 单位sp
     */
    public CustomEditText setTextSize(float size) {
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    /**
     * 自定义软键盘Enter键
     *
     * @param action EditorInfo.IME_ACTION_NEXT
     */
    public CustomEditText setEnterAction(int action) {
        mEditText.setImeOptions(action);
        return this;
    }

    /**
     * 设置输入类型
     *
     * @param type InputType.TYPE_TEXT_VARIATION_NORMAL
     */
    public CustomEditText setInputType(int type) {
        mEditText.setImeOptions(type);
        return this;
    }

    /**
     * 获取输入内容
     */
    public String getEditText() {
        return mEditText.getText().toString();
    }
}
