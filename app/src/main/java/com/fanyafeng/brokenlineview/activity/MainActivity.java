package com.fanyafeng.brokenlineview.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.brokenlineview.R;
import com.fanyafeng.brokenlineview.BaseActivity;
import com.fanyafeng.brokenlineview.view.BrokenLineView;

import java.util.ArrayList;
import java.util.List;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class MainActivity extends BaseActivity {
    private BrokenLineView blvMain;
    private List<Point> points = new ArrayList<>();
    private List<String> XindexString = new ArrayList<>();
    private List<String> YindexString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_main);
        isSetNavigationIcon = false;
        initView();
        initData();
    }


    //初始化UI控件
    private void initView() {
        blvMain = (BrokenLineView) findViewById(R.id.blvMain);
    }

    //初始化数据
    private void initData() {
        for (int i = 0; i < 6; i++) {
            Point point = new Point(0, i * 140 + 120);
            points.add(point);
            XindexString.add("第" + i + "个");
            YindexString.add("第" + i + "个");
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnBrokenLine:
                blvMain.setPointList(points);
                break;
            case R.id.btnDrawX:
                blvMain.setXIndex(XindexString);
                break;
            case R.id.btnDrawY:
                blvMain.setYIndex(YindexString);
                break;
            case R.id.btnDrawShape:
                blvMain.setShapePaints(new Point(0, 300), new Point(0, 600));
                break;
        }
    }
}
