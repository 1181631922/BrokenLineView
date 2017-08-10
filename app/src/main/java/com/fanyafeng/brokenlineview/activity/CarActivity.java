package com.fanyafeng.brokenlineview.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanyafeng.brokenlineview.R;
import com.fanyafeng.brokenlineview.BaseActivity;
import com.fanyafeng.brokenlineview.util.DpPxConvert;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class CarActivity extends BaseActivity {
    private TextView tvCarTitle;
    private ImageView ivCarIcon;
    private RelativeLayout layoutCarBackground;

    private int textWidth;
    private int backgroundWidth;
    private boolean canClick = false;
    private boolean isShowWheel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_car);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_car));
    }

    //初始化UI控件
    private void initView() {
        layoutCarBackground = (RelativeLayout) findViewById(R.id.layoutCarBackground);
        layoutCarBackground.setVisibility(View.INVISIBLE);
        layoutCarBackground.getBackground().setAlpha(120);
        tvCarTitle = (TextView) findViewById(R.id.tvCarTitle);
        tvCarTitle.setVisibility(View.GONE);
        ivCarIcon = (ImageView) findViewById(R.id.ivCarIcon);
        ivCarIcon.setVisibility(View.INVISIBLE);
        tvCarTitle.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (textWidth == 0) {
                    textWidth = tvCarTitle.getWidth();
                } else {
                    tvCarTitle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        layoutCarBackground.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (backgroundWidth == 0) {
                    backgroundWidth = layoutCarBackground.getWidth();
                } else {
                    layoutCarBackground.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        ivCarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClick) {
                    if (isShowWheel) {
                        ObjectAnimator moveRightAnimator = ObjectAnimator.ofFloat(ivCarIcon, "translationX", 0, DpPxConvert.dip2px(CarActivity.this, 40));
                        moveRightAnimator.setDuration(500);
                        moveRightAnimator.start();

                        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, 360f * 12);
                        rotateAnimator.setDuration(500);
                        rotateAnimator.start();

                        ObjectAnimator rotateAnimator2 = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, 360f * 2);
                        rotateAnimator2.setDuration(1000);
                        rotateAnimator2.start();

                        isShowWheel = false;
                    } else {
                        ObjectAnimator moveRightAnimator = ObjectAnimator.ofFloat(ivCarIcon, "translationX", DpPxConvert.dip2px(CarActivity.this, 40), 0);
                        moveRightAnimator.setDuration(500);
                        moveRightAnimator.start();

                        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, -360f * 15);
                        rotateAnimator.setDuration(500);
                        rotateAnimator.start();

                        ObjectAnimator rotateAnimator2 = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, -360f * 2);
                        rotateAnimator2.setDuration(1000);
                        rotateAnimator2.start();

                        isShowWheel = true;
                    }
                }
            }
        });
    }

    //初始化数据
    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnRotate:
//                ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, 3600f);
//                rotateAnimator.setDuration(1500);
//                rotateAnimator.start();
                break;
            case R.id.btnMoveLeft:
//                ivCarIcon.setVisibility(View.VISIBLE);
//                ObjectAnimator moveLeftAnimator = ObjectAnimator.ofFloat(ivCarIcon, "translationX", DpPxConvert.dip2px(this, 66), 0);
//                moveLeftAnimator.setDuration(1500);
//                moveLeftAnimator.start();
                break;
            case R.id.btnMoveRight:
//                ivCarIcon.setVisibility(View.VISIBLE);
//                ObjectAnimator moveRightAnimator = ObjectAnimator.ofFloat(ivCarIcon, "translationX", 0, DpPxConvert.dip2px(this, 30));
//                moveRightAnimator.setDuration(1500);
//                moveRightAnimator.start();
                break;
            case R.id.btnScale:
//                ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.scale);
//                tvCarTitle.startAnimation(scaleAnimation);
//                tvCarTitle.setVisibility(View.VISIBLE);
//                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
//                scaleAnimation.setDuration(200);
//                tvCarTitle.startAnimation(scaleAnimation);
                break;
            case R.id.btnScaleRight:
                ScaleAnimation scaleRightAnimation = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleRightAnimation.setDuration(200);
                scaleRightAnimation.setFillAfter(true);
                tvCarTitle.startAnimation(scaleRightAnimation);
//
//                ScaleAnimation scaleBackGroundAnimation = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
//                scaleBackGroundAnimation.setDuration(200);
//                scaleBackGroundAnimation.setFillAfter(true);
//                layoutCarBackground.startAnimation(scaleBackGroundAnimation);
//                tvCarTitle.setVisibility(View.GONE);
                break;
            case R.id.btnBackgroundScale:
//                layoutCarBackground.setVisibility(View.VISIBLE);
//                ScaleAnimation scaleBackgroundAnimation = new ScaleAnimation(0.0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
//                scaleBackgroundAnimation.setDuration(200);
//                layoutCarBackground.startAnimation(scaleBackgroundAnimation);
                break;
            case R.id.btnBegin:
                ivCarIcon.setVisibility(View.VISIBLE);

                ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, -360f * 15);
                rotateAnimator.setDuration(500);
                rotateAnimator.start();

                ObjectAnimator moveLeftAnimator = ObjectAnimator.ofFloat(ivCarIcon, "translationX", DpPxConvert.dip2px(this, 66), -DpPxConvert.dip2px(this, 16));
                moveLeftAnimator.setDuration(500);
                moveLeftAnimator.start();

                ObjectAnimator rotateAnimator1 = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, -360f * 2);
                rotateAnimator1.setDuration(1000);
                rotateAnimator1.start();

                tvCarTitle.setVisibility(View.VISIBLE);

                ScaleAnimation scaleBackgroundAnimation1 = new ScaleAnimation(0.0f, 0.4f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleBackgroundAnimation1.setDuration(30);
                scaleBackgroundAnimation1.setStartOffset(1000);
                scaleBackgroundAnimation1.setFillAfter(true);

                layoutCarBackground.startAnimation(scaleBackgroundAnimation1);
                tvCarTitle.startAnimation(scaleBackgroundAnimation1);

                ScaleAnimation scaleBackgroundAnimation2 = new ScaleAnimation(0.0f, 0.65f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleBackgroundAnimation2.setDuration(60);
                scaleBackgroundAnimation2.setStartOffset(1000);
                scaleBackgroundAnimation2.setFillAfter(true);

                layoutCarBackground.startAnimation(scaleBackgroundAnimation2);
                tvCarTitle.startAnimation(scaleBackgroundAnimation2);

                ScaleAnimation scaleBackgroundAnimation3 = new ScaleAnimation(0.0f, 0.85f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleBackgroundAnimation3.setDuration(90);
                scaleBackgroundAnimation3.setStartOffset(1000);
                scaleBackgroundAnimation3.setFillAfter(true);

                layoutCarBackground.startAnimation(scaleBackgroundAnimation3);
                tvCarTitle.startAnimation(scaleBackgroundAnimation3);

                ScaleAnimation scaleBackgroundAnimation4 = new ScaleAnimation(0.0f, 0.95f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleBackgroundAnimation4.setDuration(120);
                scaleBackgroundAnimation4.setStartOffset(1000);
                scaleBackgroundAnimation4.setFillAfter(true);

                layoutCarBackground.startAnimation(scaleBackgroundAnimation4);
                tvCarTitle.startAnimation(scaleBackgroundAnimation4);

                ScaleAnimation scaleBackgroundAnimation5 = new ScaleAnimation(0.0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleBackgroundAnimation5.setDuration(150);
                scaleBackgroundAnimation5.setStartOffset(1000);
                scaleBackgroundAnimation5.setFillAfter(true);

                AlphaAnimation alphaAnimation1 = new AlphaAnimation(0f, 1f);
                alphaAnimation1.setDuration(150);
                alphaAnimation1.setFillAfter(true);
                alphaAnimation1.setStartOffset(1000);

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(alphaAnimation1);
                animationSet.addAnimation(scaleBackgroundAnimation5);
                layoutCarBackground.startAnimation(animationSet);
                tvCarTitle.startAnimation(animationSet);
                layoutCarBackground.setVisibility(View.VISIBLE);


                break;

            case R.id.btnEnd:
                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1f, 0f);
                alphaAnimation2.setDuration(150);
                alphaAnimation2.setFillAfter(true);
                alphaAnimation2.setFillBefore(false);

                ScaleAnimation scaleEndAnimation = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
                scaleEndAnimation.setDuration(150);
                scaleEndAnimation.setFillAfter(true);

                AnimationSet animationSet1 = new AnimationSet(true);
                animationSet1.addAnimation(alphaAnimation2);
                animationSet1.addAnimation(scaleEndAnimation);
                tvCarTitle.startAnimation(animationSet1);
                layoutCarBackground.startAnimation(animationSet1);

                tvCarTitle.setVisibility(View.GONE);
                layoutCarBackground.setVisibility(View.GONE);

                break;

            case R.id.btnEnd2:
                ObjectAnimator moveRightAnimator = ObjectAnimator.ofFloat(ivCarIcon, "translationX", -DpPxConvert.dip2px(this, 16), DpPxConvert.dip2px(this, 40));
                moveRightAnimator.setDuration(500);
                moveRightAnimator.start();

                ObjectAnimator rotatAnimator = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, 360f * 12);
                rotatAnimator.setDuration(500);
                rotatAnimator.start();

                ObjectAnimator rotateAnimator2 = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, 360f * 2);
                rotateAnimator2.setDuration(1000);
                rotateAnimator2.start();
                canClick = true;
                break;
        }
    }
}
