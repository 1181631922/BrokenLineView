package com.fanyafeng.brokenlineview.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.brokenlineview.R;
import com.fanyafeng.brokenlineview.BaseActivity;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_main);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_main));
    }

    //初始化UI控件
    private void initView() {
        String string = "<font size=\"3\">下图为要实现的设计图，点击后为初步实现的效果图。</font>";
    }

    //初始化数据
    private void initData() {

    }

}
