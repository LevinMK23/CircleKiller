package com.samsung.circlekiller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.TimeUnit;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    TestThread thread;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new TestThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        thread.running = true;
        thread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.running = false;
        while (thread.isAlive()) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        thread.x = event.getX();
        thread.y = event.getY();
        thread.r = 0;
        return false;
    }

    class TestThread extends Thread {

        boolean running;
        float x, y, r;

        public TestThread() {
            x = -100;
            y = -100;
            r = -1;
        }

        void update() {
            r += 5;
        }

        @Override
        public void run() {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.YELLOW);
            running = true;
            while (running) {
                synchronized (getHolder()) {
                    Canvas canvas = getHolder().lockCanvas();
                    canvas.drawColor(Color.BLUE);
                    if (r >= 0) {
                        canvas.drawCircle(x, y, r, paint);
                    }
                    getHolder().unlockCanvasAndPost(canvas);
                    update();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
