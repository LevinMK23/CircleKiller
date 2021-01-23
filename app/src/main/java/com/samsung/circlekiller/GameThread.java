package com.samsung.circlekiller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class GameThread extends Thread {

    private final SurfaceHolder holder;
    private boolean running;
    private float cx, cy, radius;
    private int l, r, u, d;
    private float dx, dy;
    private long start;
    private Paint paint;
    private ConcurrentLinkedDeque<Circle> circles;


    public GameThread(SurfaceHolder holder) {
        this.holder = holder;
        running = true;
        radius = 60;
        cx = 60;
        cy = 60;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        dx = dy = 5;
        start = System.currentTimeMillis();
        circles = new ConcurrentLinkedDeque<>();
        circles.add(new Circle(cx, cy));
    }

    public void addNewCircle(Float x, Float y) {
        circles.add(new Circle(x, y));
    }

    public void addSpeed() {
        for (Circle circle : circles) {
            circle.addSpeed();
        }
        // Log.d("mike", "dx = " + dx + ", dy = " + dy);
    }

    @Override
    public void run() {
        while (running) {
            synchronized (holder) {
                long current = System.currentTimeMillis();
                Canvas canvas = holder.lockCanvas();
                draw(canvas);
                updateParams();
                start = current;
                holder.unlockCanvasAndPost(canvas);
                try {
                    TimeUnit.MICROSECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (Circle circle : circles) {
            canvas.drawCircle(circle.getX(), circle.getY(), circle.getR(), paint);
        }
    }

    private void updateParams() {
        Rect rect = holder.getSurfaceFrame();
        for (Circle circle : circles) {
            circle.setWindowRect(rect);
            circle.update();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public SurfaceHolder getHolder() {
        return holder;
    }

}
