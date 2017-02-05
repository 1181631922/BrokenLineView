package com.fanyafeng.brokenlineview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： fanyafeng
 * Data： 17/2/4 下午2:54
 * Email: fanyafeng@live.cn
 */
public class BrokenLineView extends View {

    private float lineWidth = 8;

    private static float startX = 0;
    private static float endX = 0;
    private static float startY = 0;
    private static float endY = 0;
    private static float height = 0;
    private static float width = 0;
    private static float pWidth = 0;
    private static float indexWidth = 100;

    private Paint XPaint;
    private Paint YPaint;
    private Paint pointPaint;
    private Paint circlePaint;
    private Paint bigCirclePaint;
    private Paint shapePaint;

    private List<Point> points = new ArrayList<>();
    private List<String> XindexString = new ArrayList<>();
    private List<String> YindexString = new ArrayList<>();
    private Point PointY1 = new Point(0, -1);
    private Point PointY2 = new Point(0, -1);

    public void setPointList(List<Point> points) {
        this.points = points;
        invalidate();
    }

    public void setShapePaints(Point pointY1, Point pointY2) {
        this.PointY1 = pointY1;
        this.PointY2 = pointY2;
        invalidate();
    }

    public void setXIndex(List<String> XindexString) {
        this.XindexString = XindexString;
        invalidate();
    }

    public void setYIndex(List<String> YindexString) {
        this.YindexString = YindexString;
        invalidate();
    }

    public BrokenLineView(Context context) {
        super(context);
        initView();
    }

    public BrokenLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BrokenLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        XPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        XPaint.setAntiAlias(true);
        XPaint.setColor(Color.BLUE);
        XPaint.setStrokeWidth(lineWidth / 2);

        YPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        YPaint.setAntiAlias(true);
        YPaint.setColor(Color.BLUE);
        YPaint.setStrokeWidth(lineWidth / 2);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.RED);
        pointPaint.setStrokeWidth(2);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(1);

        bigCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(Color.RED);
        bigCirclePaint.setStyle(Paint.Style.FILL);
        bigCirclePaint.setStrokeWidth(1);

        shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shapePaint.setAntiAlias(true);
        shapePaint.setColor(Color.GREEN);
        shapePaint.setStyle(Paint.Style.FILL);
        shapePaint.setStrokeWidth(1);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (startX == 0) {
            startX = getLeft();
        }
        if (endX == 0) {
            endX = getRight();
        }
        if (startY == 0) {
            startY = getTop();
        }
        if (endY == 0) {
            endY = getBottom();
        }
        if (width == 0) {//宽
            width = getWidth();
            pWidth = width - indexWidth;
        }
        if (height == 0) {//高
            height = getHeight() - indexWidth;
        }

//        //y轴第一个点
//        float pointY1y = height / 3;
//        float pointY1x = indexWidth;
//        PointY1 = new Point((int) pointY1x, (int) pointY1y);
//        //y轴第二个点
//        float pointY2y = height * 2 / 3;
//        float pointY2x = indexWidth;
//        PointY2 = new Point((int) pointY2x, (int) pointY2y);
        if (PointY1.y > -1 && PointY2.y > -1) {
            canvas.drawRect(indexWidth, PointY1.y, width, PointY2.y, shapePaint);

            //y轴坐标1
            Paint paintY1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintY1.setTextSize(30);
            paintY1.setAntiAlias(true);
            Rect rectY1 = new Rect(0, (int) PointY1.y - 10, (int) indexWidth, (int) PointY1.y + 10);//折线为圈，需要减去半径
            paintY1.setColor(Color.WHITE);
            canvas.drawRect(rectY1, paintY1);
            Paint.FontMetricsInt fontMetricsIntY1 = paintY1.getFontMetricsInt();
            int baseLineY1 = (rectY1.bottom + rectY1.top - fontMetricsIntY1.bottom - fontMetricsIntY1.top) / 2;
            paintY1.setTextAlign(Paint.Align.CENTER);
            paintY1.setColor(Color.BLACK);
            canvas.drawText("y1点", rectY1.centerX(), baseLineY1, paintY1);

            //y轴坐标2
            Paint paintY2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintY2.setTextSize(30);
            paintY2.setAntiAlias(true);
            Rect rectY2 = new Rect(0, (int) PointY2.y - 10, (int) indexWidth, (int) PointY2.y + 10);//折线为圈，需要减去半径
            paintY2.setColor(Color.WHITE);
            canvas.drawRect(rectY2, paintY2);
            Paint.FontMetricsInt fontMetricsIntY2 = paintY2.getFontMetricsInt();
            int baseLineY2 = (rectY2.bottom + rectY2.top - fontMetricsIntY2.bottom - fontMetricsIntY2.top) / 2;
            paintY2.setTextAlign(Paint.Align.CENTER);
            paintY2.setColor(Color.BLACK);
            canvas.drawText("y2点", rectY2.centerX(), baseLineY2, paintY2);
        }

//        //x轴第一个点,y轴第一个
//        float x1 = pWidth / 6;
//        float y1 = height / 6;
//
//        //x轴第二个点
//        float x2 = pWidth / 3;
//        float y2 = height / 3;
//
//        //x轴第三个点
//        float x3 = pWidth / 2;
//        float y3 = height / 2;
//
//        //x轴第四个点
//        float x4 = 2 * pWidth / 3;
//        float y4 = height / 3;
//
//        //x轴第五个点
//        float x5 = 5 * pWidth / 6;
//        float y5 = 5 * height / 6;
//
//        //以下是折线图
//        canvas.drawLine(x1, y1, x2, y2, pointPaint);
//        canvas.drawLine(x2, y2, x3, y3, pointPaint);
//        canvas.drawLine(x3, y3, x4, y4, pointPaint);
//        canvas.drawLine(x4, y4, x5, y5, pointPaint);
//
//        canvas.drawCircle(x1, y1, 12, bigCirclePaint);
//        canvas.drawCircle(x1, y1, 10, circlePaint);
//
//        //折点标字
//        Paint paintX1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintX1.setAntiAlias(true);
//        paintX1.setTextSize(30);
//        Rect rect = new Rect((int) x1 - 50, (int) y1 - 70, (int) x1 + 50, (int) y1 - 20);//折线为圈，需要减去半径
//        paintX1.setColor(Color.WHITE);
//        canvas.drawRect(rect, paintX1);
//        Paint.FontMetricsInt fontMetricsIntX1 = paintX1.getFontMetricsInt();
//        int baseLineX1 = (rect.bottom + rect.top - fontMetricsIntX1.bottom - fontMetricsIntX1.top) / 2;
//        paintX1.setTextAlign(Paint.Align.CENTER);
//        paintX1.setColor(Color.BLACK);
//        canvas.drawText("26.9", rect.centerX(), baseLineX1, paintX1);
        //刻度
//        Paint paintX2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintX2.setAntiAlias(true);
//        paintX2.setTextSize(30);
//        Rect rectX2 = new Rect((int) x1 - 50, (int) height, (int) x1 + 50, (int) (height + indexWidth));//折线为圈，需要减去半径
//        paintX2.setColor(Color.WHITE);
//        canvas.drawRect(rectX2, paintX2);
//        Paint.FontMetricsInt fontMetricsIntX2 = paintX2.getFontMetricsInt();
//        int baseLineX2 = (rectX2.bottom + rectX2.top - fontMetricsIntX2.bottom - fontMetricsIntX2.top) / 2;
//        paintX2.setTextAlign(Paint.Align.CENTER);
//        paintX2.setColor(Color.BLACK);
//        canvas.drawText("26.9", rectX2.centerX(), baseLineX2, paintX2);


//        canvas.drawCircle(x2, y2, 12, bigCirclePaint);
//        canvas.drawCircle(x2, y2, 10, circlePaint);
//
//        canvas.drawCircle(x3, y3, 12, bigCirclePaint);
//        canvas.drawCircle(x3, y3, 10, circlePaint);
//
//        canvas.drawCircle(x4, y4, 12, bigCirclePaint);
//        canvas.drawCircle(x4, y4, 10, circlePaint);
//
//        canvas.drawCircle(x5, y5, 12, bigCirclePaint);
//        canvas.drawCircle(x5, y5, 10, circlePaint);

        //以上是折线图

        int size = points.size();
        if (size != 0) {
            //size+1份，每份长度
            float eachLength = pWidth / (size + 1);

            for (int i = 0; i < size; i++) {
                float pointX1 = indexWidth + eachLength * (i + 1);
                float pointY1 = points.get(i).y;

                if (i != size - 1) {
                    float pointX2 = indexWidth + eachLength * (i + 2);
                    float pointY2 = points.get(i + 1).y;
                    canvas.drawLine(pointX1, pointY1, pointX2, pointY2, pointPaint);

                    canvas.drawCircle(pointX2, pointY2, 12, bigCirclePaint);
                    canvas.drawCircle(pointX2, pointY2, 10, circlePaint);
                }
                canvas.drawCircle(pointX1, pointY1, 12, bigCirclePaint);
                canvas.drawCircle(pointX1, pointY1, 10, circlePaint);

                //折点标字
                Paint paintBroken = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintBroken.setAntiAlias(true);
                paintBroken.setTextSize(30);
                Rect rectBroken = new Rect((int) pointX1 - 50, (int) pointY1 - 70, (int) pointX1 + 50, (int) pointY1 - 20);//折线为圈，需要减去半径
                paintBroken.setColor(Color.TRANSPARENT);
                canvas.drawRect(rectBroken, paintBroken);
                Paint.FontMetricsInt fontMetricsIntBroken = paintBroken.getFontMetricsInt();
                int baseLineBroken = (rectBroken.bottom + rectBroken.top - fontMetricsIntBroken.bottom - fontMetricsIntBroken.top) / 2;
                paintBroken.setTextAlign(Paint.Align.CENTER);
                paintBroken.setColor(Color.BLACK);
                canvas.drawText(String.valueOf(points.get(i).y), rectBroken.centerX(), baseLineBroken, paintBroken);

                //x刻度
                Paint paintBrokenX = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintBrokenX.setAntiAlias(true);
                paintBrokenX.setTextSize(30);
                Rect rectBrokenX = new Rect((int) pointX1 - 50, (int) height, (int) pointX1 + 50, (int) (height + indexWidth));//折线为圈，需要减去半径
                paintBrokenX.setColor(Color.TRANSPARENT);
                canvas.drawRect(rectBrokenX, paintBrokenX);
                Paint.FontMetricsInt fontMetricsIntBrokenX = paintBrokenX.getFontMetricsInt();
                int baseLineBrokenX = (rectBrokenX.bottom + rectBrokenX.top - fontMetricsIntBrokenX.bottom - fontMetricsIntBrokenX.top) / 2;
                paintBrokenX.setTextAlign(Paint.Align.CENTER);
                paintBrokenX.setColor(Color.BLACK);
                canvas.drawText(XindexString.size() > 0 ? XindexString.get(i) : String.valueOf(i + 1), rectBrokenX.centerX(), baseLineBrokenX, paintBrokenX);

                //y刻度
                Paint paintBrokenY = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintBrokenY.setTextSize(30);
                paintBrokenY.setAntiAlias(true);
                Rect rectBrokenY = new Rect(0, (int) pointY1 - 10, (int) indexWidth, (int) pointY1 + 10);//折线为圈，需要减去半径
                paintBrokenY.setColor(Color.WHITE);
                canvas.drawRect(rectBrokenY, paintBrokenY);
                Paint.FontMetricsInt fontMetricsIntBrokenY = paintBrokenY.getFontMetricsInt();
                int baseLineBrokenY = (rectBrokenY.bottom + rectBrokenY.top - fontMetricsIntBrokenY.bottom - fontMetricsIntBrokenY.top) / 2;
                paintBrokenY.setTextAlign(Paint.Align.CENTER);
                paintBrokenY.setColor(Color.BLACK);
                canvas.drawText(YindexString.size() > 0 ? YindexString.get(i) : String.valueOf(i + 1), rectBrokenY.centerX(), baseLineBrokenY, paintBrokenY);
            }
        }
        //x轴 轴应该最后画
        canvas.drawLine(indexWidth, height, width, height, XPaint);
        //y轴
        canvas.drawLine(indexWidth, 0, indexWidth, height, YPaint);

//        invalidate();
    }

}
