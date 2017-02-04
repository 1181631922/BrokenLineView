package com.fanyafeng.brokenlineview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

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
        if (width == 0) {
            width = getWidth();
            pWidth = width - indexWidth;
        }
        if (height == 0) {
            height = getHeight() - indexWidth;
        }

        //y轴第一个点
        float pointY1y = height / 3;
        float pointY1x = indexWidth;
        //y轴第二个点
        float pointY2y = height * 2 / 3;
        float pointY2x = indexWidth;
        canvas.drawRect(indexWidth, pointY1y, width, pointY2y, shapePaint);

        //x轴第一个点,y轴第一个
        float x1 = pWidth / 6;
        float y1 = height / 6;

        //x轴第二个点
        float x2 = pWidth / 3;
        float y2 = height / 3;

        //x轴第三个点
        float x3 = pWidth / 2;
        float y3 = height / 2;

        //x轴第四个点
        float x4 = 2 * pWidth / 3;
        float y4 = height / 3;

        //x轴第五个点
        float x5 = 5 * pWidth / 6;
        float y5 = 5 * height / 6;

        //以下是折线图
        canvas.drawLine(x1, y1, x2, y2, pointPaint);
        canvas.drawLine(x2, y2, x3, y3, pointPaint);
        canvas.drawLine(x3, y3, x4, y4, pointPaint);
        canvas.drawLine(x4, y4, x5, y5, pointPaint);

        canvas.drawCircle(x1, y1, 12, bigCirclePaint);
        canvas.drawCircle(x1, y1, 10, circlePaint);

        //折点标字
        Paint paintX1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintX1.setAntiAlias(true);
        paintX1.setTextSize(30);
        Rect rect = new Rect((int) x1 - 50, (int) y1 - 70, (int) x1 + 50, (int) y1 - 20);//折线为圈，需要减去半径
        paintX1.setColor(Color.WHITE);
        canvas.drawRect(rect, paintX1);
        Paint.FontMetricsInt fontMetricsIntX1 = paintX1.getFontMetricsInt();
        int baseLineX1 = (rect.bottom + rect.top - fontMetricsIntX1.bottom - fontMetricsIntX1.top) / 2;
        paintX1.setTextAlign(Paint.Align.CENTER);
        paintX1.setColor(Color.BLACK);
        canvas.drawText("26.9", rect.centerX(), baseLineX1, paintX1);

        //刻度
        Paint paintX2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintX2.setAntiAlias(true);
        paintX2.setTextSize(30);
        Rect rectX2 = new Rect((int) x1 - 50, (int) height, (int) x1 + 50, (int) (height + indexWidth));//折线为圈，需要减去半径
        paintX2.setColor(Color.WHITE);
        canvas.drawRect(rectX2, paintX2);
        Paint.FontMetricsInt fontMetricsIntX2 = paintX2.getFontMetricsInt();
        int baseLineX2 = (rectX2.bottom + rectX2.top - fontMetricsIntX2.bottom - fontMetricsIntX2.top) / 2;
        paintX2.setTextAlign(Paint.Align.CENTER);
        paintX2.setColor(Color.BLACK);
        canvas.drawText("26.9", rectX2.centerX(), baseLineX2, paintX2);


        canvas.drawCircle(x2, y2, 12, bigCirclePaint);
        canvas.drawCircle(x2, y2, 10, circlePaint);

        canvas.drawCircle(x3, y3, 12, bigCirclePaint);
        canvas.drawCircle(x3, y3, 10, circlePaint);

        canvas.drawCircle(x4, y4, 12, bigCirclePaint);
        canvas.drawCircle(x4, y4, 10, circlePaint);

        canvas.drawCircle(x5, y5, 12, bigCirclePaint);
        canvas.drawCircle(x5, y5, 10, circlePaint);

        //以上是折线图

//        y轴坐标
        Paint paintY1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintY1.setTextSize(30);
        paintY1.setAntiAlias(true);
        Rect rectY1 = new Rect(0, (int) pointY1y - 30, (int) indexWidth, (int) pointY1y + 30);//折线为圈，需要减去半径
        paintY1.setColor(Color.WHITE);
        canvas.drawRect(rectY1, paintY1);
        Paint.FontMetricsInt fontMetricsIntY1 = paintY1.getFontMetricsInt();
        int baseLineY1 = (rectY1.bottom + rectY1.top - fontMetricsIntY1.bottom - fontMetricsIntY1.top) / 2;
        paintY1.setTextAlign(Paint.Align.CENTER);
        paintY1.setColor(Color.BLACK);
        canvas.drawText("26.9", rectY1.centerX(), baseLineY1, paintY1);

        //x轴坐标
        Paint paintY2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintY2.setTextSize(30);
        paintY2.setAntiAlias(true);
        Rect rectY2 = new Rect(0, (int) pointY2y - 30, (int) indexWidth, (int) pointY2y + 30);//折线为圈，需要减去半径
        paintY2.setColor(Color.WHITE);
        canvas.drawRect(rectY2, paintY2);
        Paint.FontMetricsInt fontMetricsIntY2 = paintY2.getFontMetricsInt();
        int baseLineY2 = (rectY2.bottom + rectY2.top - fontMetricsIntY2.bottom - fontMetricsIntY2.top) / 2;
        paintY2.setTextAlign(Paint.Align.CENTER);
        paintY2.setColor(Color.BLACK);
        canvas.drawText("26.9", rectY2.centerX(), baseLineY2, paintY2);


        //x轴 轴应该最后画
        canvas.drawLine(indexWidth, height, width, height, XPaint);
        //y轴
        canvas.drawLine(indexWidth, 0, indexWidth, height, YPaint);
    }
}
