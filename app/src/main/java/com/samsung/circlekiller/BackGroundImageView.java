package com.samsung.circlekiller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BackGroundImageView extends View {

    public BackGroundImageView(Context context) {
        super(context);
    }

    public BackGroundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x0 = 0, y0 = 0, x1 = getWidth(), y1 = getHeight();
        float mx = (x1 - x0) / 2f;
        float my = (y1 - y0) / 2f;
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        for (int i = 0; i < 100; i++) {
            //paint.setColor((int) (Math.random() * 20));
            canvas.drawCircle(
                    (float) (Math.random() * x1),
                    (float) (Math.random() * y1),
                    (float) (Math.random() * x1) / 2, paint);

        }
    }
}
