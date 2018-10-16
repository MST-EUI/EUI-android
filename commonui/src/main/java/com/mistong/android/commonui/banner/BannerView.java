package com.mistong.android.commonui.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.mistong.android.commonui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaoshuai
 * @date 2018/8/21
 * @desc
 * @email liulingfeng@mistong.com
 */
public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "BannerView";

    private Context mContext;

    private ViewPager mViewPager;//实现轮播图的ViewPager

    private LinearLayout mIndicatorLayout; // 指示器

    private Handler handler;//每几秒后执行下一张的切换

    private int WHEEL = 100; // 转动

    private int WHEEL_WAIT = 101; // 等待

    private List<View> mViews = new ArrayList<>(); //需要轮播的View，数量为轮播图数量+2

    private ImageView[] mIndicators;    //指示器小圆点

    private boolean isScrolling = false; // 滚动框是否滚动着

    private boolean isCycle = true; // 是否循环，默认为true

    private boolean isWheel = true; // 是否自动轮播，默认为true

    private boolean isFull = false;//是否全屏，默认不全屏

    private int delay = 3000; // 默认轮播时间

    private int mCurrentPosition = 0; // 轮播当前位置

    private long releaseTime = 0; // 手指松开、页面不滚动时间，防止手机松开后短时间进行切换

    private ViewPagerAdapter mAdapter;

    private List<BannerEntity> infos;//数据集合

    private int imgRadius;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mContext != null && isWheel) {
                long now = System.currentTimeMillis();
                // 检测上一次滑动时间与本次之间是否有触击(手滑动)操作，有的话等待下次轮播
                if (now - releaseTime > delay - 500) {
                    handler.sendEmptyMessage(WHEEL);
                } else {
                    handler.sendEmptyMessage(WHEEL_WAIT);
                }
            }
        }
    };


    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerView);
        isFull = typedArray.getBoolean(R.styleable.BannerView_isFull, false);
        imgRadius = typedArray.getDimensionPixelSize(R.styleable.BannerView_imgRadius, 4);
        isCycle = typedArray.getBoolean(R.styleable.BannerView_isCycle, true);
        isWheel = typedArray.getBoolean(R.styleable.BannerView_isWheel, true);
        delay = typedArray.getInt(R.styleable.BannerView_delay, 3000);
        typedArray.recycle();

        this.mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    @SuppressLint("HandlerLeak")
    private void initView() {
        if (isFull) {
            LayoutInflater.from(mContext).inflate(R.layout.eui_view_banner_full, this, true);
        } else {
            LayoutInflater.from(mContext).inflate(R.layout.eui_view_banner, this, true);
        }
        mViewPager = findViewById(R.id.viewPage);
        mIndicatorLayout = findViewById(R.id.banner_indicator);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == WHEEL && mViews.size() > 0) {
                    if (!isScrolling) {
                        //当前为非滚动状态，切换到下一页
                        int position = (mCurrentPosition + 1) % mViews.size();
                        mViewPager.setCurrentItem(position);
                    }
                    releaseTime = System.currentTimeMillis();
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, delay);
                    return;
                }
                if (msg.what == WHEEL_WAIT && mViews.size() > 0) {
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, delay);
                }
            }
        };
    }

    @Override
    protected void onDetachedFromWindow() {
        if (runnable != null && handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDetachedFromWindow();
    }

    /**
     * 获取轮播图View
     *
     * @param context
     * @param url
     */
    private View getImageView(Context context, String url) {
        //添加一个ImageView，并加载图片
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(layoutParams);
        if (isFull) {
            Glide.with(context).load(url).into(imageView);
        } else {
            Glide.with(context).load(url).into(imageView);
        }
        return imageView;
    }

    /**
     * 设置指示器
     *
     * @param selectedPosition 默认指示器位置
     */
    private void setIndicator(int selectedPosition) {
        try {
            for (ImageView imageView :
                    mIndicators) {
                imageView.setSelected(false);
            }
            if (mIndicators.length > selectedPosition)
                mIndicators[selectedPosition].setSelected(true);
        } catch (Exception e) {
            Log.i(TAG, "指示器路径不正确");
        }
    }

    /**
     * 页面适配器 返回对应的view
     */
    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public View instantiateItem(@NonNull ViewGroup container, final int position) {
            View v = mViews.get(position);
            if (imageCycleViewListener != null) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (isCycle) {
                                imageCycleViewListener.onImageClick(infos.get(mCurrentPosition - 1), mCurrentPosition - 1);
                            } else {
                                imageCycleViewListener.onImageClick(infos.get(mCurrentPosition), mCurrentPosition);
                            }
                        } catch (Exception e) {
                            //点击出现空指针
                        }
                    }
                });
            }
            container.addView(v);
            return v;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public void onPageScrolled(
            int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        int max = mViews.size() - 1;
        int position = arg0;
        mCurrentPosition = arg0;
        if (isCycle) {
            if (arg0 == 0) {
                //滚动到mView的1个（界面上的最后一个），将mCurrentPosition设置为max - 1
                mCurrentPosition = max - 1;
            } else if (arg0 == max) {
                //滚动到mView的最后一个（界面上的第一个），将mCurrentPosition设置为1
                mCurrentPosition = 1;
            }
            position = mCurrentPosition - 1;
        }

        setIndicator(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1) { // viewPager在滚动
            isScrolling = true;
            return;
        } else if (state == 0) { // viewPager滚动结束
            releaseTime = System.currentTimeMillis();
            //跳转到第mCurrentPosition个页面（没有动画效果，实际效果页面上没变化）
            mViewPager.setCurrentItem(mCurrentPosition, false);
        }
        isScrolling = false;
    }

    /******************************************************************************************************/
    /**                             对外API                                                               **/
    /******************************************************************************************************/

    public void setData(List<BannerEntity> list) {
        setData(list, 0);
    }

    /**
     * 初始化viewpager
     *
     * @param list         要显示的数据
     * @param showPosition 默认显示位置
     */
    public void setData(List<BannerEntity> list,
                        int showPosition) {
        if (list == null || list.size() == 0) {
            //没有数据时隐藏整个布局
            this.setVisibility(View.GONE);
            return;
        }

        if (list.size() == 1) {
            isCycle = false;
            isWheel = false;
        }

        mViews.clear();
        infos = list;
        if (isCycle) {
            //添加轮播图View，数量为集合数+2
            // 将最后一个View添加进来
            mViews.add(getImageView(mContext, infos.get(infos.size() - 1).path));
            for (int i = 0; i < infos.size(); i++) {
                mViews.add(getImageView(mContext, infos.get(i).path));
            }
            // 将第一个View添加进来
            mViews.add(getImageView(mContext, infos.get(0).path));
        } else {
            //只添加对应数量的View
            for (int i = 0; i < infos.size(); i++) {
                mViews.add(getImageView(mContext, infos.get(i).path));
            }
        }

        int ivSize = mViews.size();
        // 设置指示器
        mIndicators = new ImageView[ivSize];
        if (isCycle)
            mIndicators = new ImageView[ivSize - 2];
        mIndicatorLayout.removeAllViews();
        for (int i = 0; i < mIndicators.length; i++) {
            mIndicators[i] = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, dp2px(8), 0);
            mIndicators[i].setLayoutParams(lp);
            mIndicators[i].setBackgroundResource(R.drawable.eui_banner_guide_bg);
            mIndicators[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            mIndicatorLayout.addView(mIndicators[i]);
        }
        mAdapter = new ViewPagerAdapter();
        // 默认指向第一项，下方viewPager.setCurrentItem将触发重新计算指示器指向
        setIndicator(0);

        if (isFull) {
            mViewPager.setPageMargin(dp2px(0));
            mIndicatorLayout.setGravity(Gravity.CENTER);
            mIndicatorLayout.setPadding(0, 0, 0, 0);
        } else {
            mViewPager.setPageMargin(dp2px(4));
            mIndicatorLayout.setGravity(Gravity.END);
            mIndicatorLayout.setPadding(0, 0, dp2px(32), 0);
        }
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.clearOnPageChangeListeners();
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(mAdapter);
        if (showPosition < 0 || showPosition >= mViews.size())
            showPosition = 0;
        if (isCycle) {
            showPosition = showPosition + 1;
        }
        mViewPager.setCurrentItem(showPosition, false);
        setWheel(isWheel);//设置轮播
    }

    /**
     * 是否循环，默认开启。必须在setData前调用
     *
     * @param isCycle 是否循环
     */
    public void setCycle(boolean isCycle) {
        this.isCycle = isCycle;
    }

    /**
     * 是否处于循环状态
     *
     * @return
     */
    public boolean isCycle() {
        return isCycle;
    }

    /**
     * 设置是否自动轮播，默认自动轮播,轮播一定是循环的
     *
     * @param isWheel
     */
    public void setWheel(boolean isWheel) {
        this.isWheel = isWheel;
        if (isWheel) {
            isCycle = true;
            handler.postDelayed(runnable, delay);
        } else {
            handler.removeCallbacks(runnable);
        }
    }

    /**
     * 刷新数据，当外部视图更新后，通知刷新数据
     */
    public void refreshData() {
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    /**
     * 是否处于轮播状态
     *
     * @return
     */
    public boolean isWheel() {
        return isWheel;
    }

    /**
     * 设置轮播暂停时间,单位毫秒（默认3000毫秒）
     *
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    private int dp2px(float dpVal) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
    }

    private ImageCycleViewListener imageCycleViewListener;

    public void setImageCycleViewListener(ImageCycleViewListener imageCycleViewListener) {
        this.imageCycleViewListener = imageCycleViewListener;
    }

    /**
     * 轮播控件的监听事件
     */
    public interface ImageCycleViewListener {

        /**
         * 单击图片事件
         *
         * @param info
         */
        void onImageClick(BannerEntity info, int position);
    }

}
