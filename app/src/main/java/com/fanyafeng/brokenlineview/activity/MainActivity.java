package com.fanyafeng.brokenlineview.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.fanyafeng.brokenlineview.R;
import com.fanyafeng.brokenlineview.BaseActivity;
import com.fanyafeng.brokenlineview.view.BrokenLinePointBean;
import com.fanyafeng.brokenlineview.view.BrokenLineView;

import java.util.ArrayList;
import java.util.List;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class MainActivity extends BaseActivity {
    private BrokenLineView blvMain;
    private List<BrokenLinePointBean> points = new ArrayList<>();
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
//        for (int i = 0; i < 6; i++) {
//            BrokenLinePointBean point = new BrokenLinePointBean(0, (float) (i * 0.15 + 0.1));
//            points.add(point);
//            XindexString.add("第" + i + "个");
//            YindexString.add("第" + i + "个");
//        }

        Point pointY1 = new Point(0, 80);//绿色范围最高点
        Point pointY2 = new Point(0, 20);//绿色范围最低点

        Point pointMax = new Point(0, 90);//如果数据不正常超过范围最高点的最大值
        Point pointMin = new Point(0, 10);//如果数据不正常超过范围最低点的最小值

        Point point1 = new Point(0, 70);//正常值
        Point point2 = new Point(0, 50);
        Point point3 = new Point(0, 30);

        List<Point> tempPointList = new ArrayList<>();
        tempPointList.add(pointMax);
        tempPointList.add(pointMin);
        tempPointList.add(point1);
        tempPointList.add(point2);
        tempPointList.add(point3);

        int maxY = pointY1.y;
        int minY = pointY2.y;

        for (int i = 0; i < tempPointList.size(); i++) {
            maxY = Math.max(maxY, tempPointList.get(i).y);
            minY = Math.min(minY, tempPointList.get(i).y);
        }

        Log.d("max", "最大y值:" + maxY);
        Log.d("min", "最小y值:" + minY);

        int YIndexLength = maxY - minY + minY;

        brokenLinePointBeanY1 = new BrokenLinePointBean(0, 1 - (float) (pointY1.y - minY / 2) / (float) YIndexLength);
        brokenLinePointBeanY2 = new BrokenLinePointBean(0, 1 - (float) (pointY2.y - minY / 2) / (float) YIndexLength);

        for (int i = 0; i < tempPointList.size(); i++) {
            Point point = tempPointList.get(i);
            Log.d("point", "pointy:" + point.y);
            Log.d("point", "比例：" + (float) point.y / (float) YIndexLength);
            BrokenLinePointBean brokenLinePointBean = new BrokenLinePointBean(0, 1 - (float) (point.y - minY / 2) / (float) YIndexLength);
            points.add(brokenLinePointBean);
        }

    }

    private BrokenLinePointBean brokenLinePointBeanY1;
    private BrokenLinePointBean brokenLinePointBeanY2;

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
                blvMain.setShapePaints(brokenLinePointBeanY1, brokenLinePointBeanY2);
                break;
        }
    }
}
