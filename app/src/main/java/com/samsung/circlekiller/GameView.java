package com.samsung.circlekiller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private GameThread gameThread;
    private SurfaceHolder holder;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        setOnTouchListener(this);
        gameThread = new GameThread(holder);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameThread.start();
        Log.d("mike", "Thread started");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder,
                               int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameThread.setRunning(false);
        while (gameThread.isAlive()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gameThread.addSpeed();
        gameThread.addNewCircle(60f, 60f);
        return false;
    }
}
