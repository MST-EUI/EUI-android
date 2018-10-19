package com.mistong.android.commonui.list;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.mistong.android.commonui.R;
import com.mistong.android.commonui.utils.DensityUtils;

/**
 * @Date 2018/10/18
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class InfoView extends ConstraintLayout {

    private enum RightMode {
        RIGHT_MODE_ARROW(0),
        RIGHT_MODE_TOGGLE(1),
        RIGHT_MODE_CUSTOM(2),
        RIGHT_MODE_TEXT(3),
        RIGHT_MODE_NONE(-1);
        int mode;

        RightMode(int mode) {
            this.mode = mode;
        }

        static RightMode fromId(int mode) {
            for (RightMode rt : values()) {
                if (rt.mode == mode) return rt;
            }
            return RIGHT_MODE_NONE;
        }
    }

    private enum ImageSize {
        NORMAL(0),
        LARGE(1);
        int type;

        ImageSize(int type) {
            this.type = type;
        }

        static ImageSize fromId(int type) {
            for (ImageSize is : values()) {
                if (is.type == type) return is;
            }
            return NORMAL;
        }
    }

    private boolean mShowBottomDivider;
    private boolean mShowLeftImage;
    private boolean mShowSubText;
    private RightMode mRightMode;
    private ImageSize mLeftImageSize;
    private String mMainText;
    private String mSubText;
    private int mLeftImgRes;
    private int mRightImgRes;
    private float mRightMargin;
    private String mRightText;

    private ImageView iv_icon;
    private TextView tv_main;
    private TextView tv_sub;
    private TextView tv_right;
    private ImageView iv_right;
    private ToggleButton btn_toggle;
    private ImageView iv_arrow;
    private View divider;

    public InfoView(Context context) {
        this(context, null, 0);
    }

    public InfoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.eui_info_view, this);

        iv_icon = findViewById(R.id.iv_icon);
        tv_main = findViewById(R.id.tv_main);
        tv_sub = findViewById(R.id.tv_sub);
        tv_right = findViewById(R.id.tv_right);
        tv_right = findViewById(R.id.tv_right);
        iv_right = findViewById(R.id.iv_right);
        btn_toggle = findViewById(R.id.btn_toggle);
        iv_arrow = findViewById(R.id.iv_arrow);
        divider = findViewById(R.id.divider);

        if (attrs == null) {
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.InfoView);
        mShowBottomDivider = ta.getBoolean(R.styleable.InfoView_showBottomDivider, true);
        mShowLeftImage = ta.getBoolean(R.styleable.InfoView_showLeftImage, false);
        mShowSubText = ta.getBoolean(R.styleable.InfoView_showSubText, false);
        mRightMode = RightMode.fromId(ta.getInt(R.styleable.InfoView_rightMode, -1));
        mLeftImageSize = ImageSize.fromId(ta.getInt(R.styleable.InfoView_LeftImageSize, -1));
        mMainText = ta.getString(R.styleable.InfoView_mainText);
        mSubText = ta.getString(R.styleable.InfoView_subText);
        mRightImgRes = ta.getResourceId(R.styleable.InfoView_rightIcon, R.drawable.check);
        mLeftImgRes = ta.getResourceId(R.styleable.InfoView_leftIcon, 0);
        mRightMargin = ta.getDimension(R.styleable.InfoView_rightMargin, 12.0f);
        mRightText = ta.getString(R.styleable.InfoView_rightText);
        ta.recycle();

        divider.setVisibility(mShowBottomDivider?VISIBLE:GONE);
        iv_icon.setVisibility(mShowLeftImage?VISIBLE:GONE);
        if (ImageSize.LARGE == mLeftImageSize) {
            ViewGroup.LayoutParams lp = iv_icon.getLayoutParams();
            lp.width = DensityUtils.dp2px(getContext(), 44);
            lp.height = DensityUtils.dp2px(getContext(), 44);
            iv_icon.setLayoutParams(lp);
        }
        if (!TextUtils.isEmpty(mMainText)) {
            tv_main.setText(mMainText);
        }
        tv_sub.setVisibility(mShowSubText?VISIBLE:GONE);
        if (!TextUtils.isEmpty(mSubText)) {
            tv_sub.setText(mSubText);
        }
        iv_icon.setImageResource(mLeftImgRes);
        iv_arrow.setVisibility(RightMode.RIGHT_MODE_ARROW == mRightMode?VISIBLE:GONE);
        iv_right.setVisibility(RightMode.RIGHT_MODE_CUSTOM == mRightMode?VISIBLE:GONE);
        btn_toggle.setVisibility(RightMode.RIGHT_MODE_TOGGLE == mRightMode?VISIBLE:GONE);
        tv_right.setVisibility((RightMode.RIGHT_MODE_ARROW == mRightMode || RightMode.RIGHT_MODE_TEXT == mRightMode)?VISIBLE:GONE);
        if (!TextUtils.isEmpty(mRightText)) {
            tv_right.setText(mRightText);
        }
        iv_right.setImageResource(mRightImgRes);

        //右侧边距
        ConstraintLayout.LayoutParams lp;
        int margin = DensityUtils.dp2px(getContext(), mRightMargin);
        if (RightMode.RIGHT_MODE_ARROW == mRightMode) {
            lp = (ConstraintLayout.LayoutParams) iv_arrow.getLayoutParams();
            lp.rightMargin = margin;
            iv_arrow.setLayoutParams(lp);
        } else if (RightMode.RIGHT_MODE_CUSTOM == mRightMode) {
            lp = (ConstraintLayout.LayoutParams) iv_right.getLayoutParams();
            lp.rightMargin = margin;
            iv_right.setLayoutParams(lp);
        } else if (RightMode.RIGHT_MODE_TOGGLE == mRightMode) {
            lp = (ConstraintLayout.LayoutParams) btn_toggle.getLayoutParams();
            lp.rightMargin = margin;
            btn_toggle.setLayoutParams(lp);
        } else if (RightMode.RIGHT_MODE_TEXT == mRightMode) {
            lp = (ConstraintLayout.LayoutParams) tv_right.getLayoutParams();
            lp.goneEndMargin = margin;
            tv_right.setLayoutParams(lp);
        }
    }

    /**
     * 设置右侧toggle监听器
     * @param listener listener
     */
    public void setRightToggleListener(CompoundButton.OnCheckedChangeListener listener) {
        btn_toggle.setOnCheckedChangeListener(listener);
    }


}
