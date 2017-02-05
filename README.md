# BrokenLineView
折线图，~~未全部完成，待修改~~ <br>
已经完成，可以传入point数组，以及两个point（确定阴影范围），构造方法可以自己写，这样可以不要阴影范围。<br>
我这里写了几个方法可供选择<br>
* setPointList(List<BrokenLinePointBean> points)<br>
* setShapePaints(BrokenLinePointBean pointY1, BrokenLinePointBean pointY2)<br>
* setXIndex(List<String> XindexString)<br>
* setYIndex(List<String> YindexString)<br>

### 1.Gif效果图
![gif效果图](https://github.com/1181631922/BrokenLineView/blob/master/img/brokenview.gif)

### 2.思路
我刚开始传的是point，但是这是个坑，传入需要百分比，我定义了一个bean，我这里传入的y值是float这样能够引起使用者注意，这里取值是[0,1]。<br>
x轴为传入的list的个数加1份的等分，所以基本x用不到。<br>

### 3.核心代码
```
if (PointY1.y > -1 && PointY2.y > -1) {
            canvas.drawRect(indexWidth, PointY1.y * height, width, PointY2.y * height, shapePaint);

            //y轴坐标1
            Paint paintY1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintY1.setTextSize(30);
            paintY1.setAntiAlias(true);
            Rect rectY1 = new Rect(0, (int) (PointY1.y * height - 10), (int) indexWidth, (int) (PointY1.y * height + 10));//折线为圈，需要减去半径
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
            Rect rectY2 = new Rect(0, (int) (PointY2.y * height - 10), (int) indexWidth, (int) (PointY2.y * height + 10));//折线为圈，需要减去半径
            paintY2.setColor(Color.WHITE);
            canvas.drawRect(rectY2, paintY2);
            Paint.FontMetricsInt fontMetricsIntY2 = paintY2.getFontMetricsInt();
            int baseLineY2 = (rectY2.bottom + rectY2.top - fontMetricsIntY2.bottom - fontMetricsIntY2.top) / 2;
            paintY2.setTextAlign(Paint.Align.CENTER);
            paintY2.setColor(Color.BLACK);
            canvas.drawText("y2点", rectY2.centerX(), baseLineY2, paintY2);
        }

        int size = points.size();
        if (size != 0) {
            //size+1份，每份长度
            float eachLength = pWidth / (size + 1);

            for (int i = 0; i < size; i++) {
                float pointX1 = indexWidth + eachLength * (i + 1);
                float pointY1 = points.get(i).y * height;

                if (i != size - 1) {
                    float pointX2 = indexWidth + eachLength * (i + 2);
                    float pointY2 = points.get(i + 1).y * height;
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
```

### 4.简单折线图
![图解](https://github.com/1181631922/BrokenLineView/blob/master/img/979DF617-A1E2-41AC-AC5C-89F9366674D3.png)
![图解2](https://github.com/1181631922/BrokenLineView/blob/master/img/F118EBBD-8C04-46D3-A453-92CB981E9B75.png)
