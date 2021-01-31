package com.samsung.circlekiller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.samsung.circlekiller.model.Bullet;

import java.util.concurrent.TimeUnit;

public class CrimsonGameView extends SurfaceView implements SurfaceHolder.Callback {

    private final CrimsonGameThread gameThread;
    private final Paint paint;

    public CrimsonGameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameThread = new CrimsonGameThread(this, getHolder());
        paint = new Paint();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameThread.setRunning(false);
        while (gameThread.isAlive()) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameThread.addBullet(new Bullet(this, paint, event.getX(), event.getY()));
        Log.d("mike", "bullet add");
        return false    ;
    }
}
