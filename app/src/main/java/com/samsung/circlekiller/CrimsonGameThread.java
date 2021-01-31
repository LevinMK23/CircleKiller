package com.samsung.circlekiller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import com.samsung.circlekiller.model.Bullet;
import com.samsung.circlekiller.model.Enemy;
import com.samsung.circlekiller.model.GameUnit;
import com.samsung.circlekiller.model.Hero;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class CrimsonGameThread extends Thread {

    private CrimsonGameView gameView;
    private final SurfaceHolder holder;
    private boolean running;
    private ConcurrentLinkedDeque<GameUnit> units;
    private final Paint heroPaint, enemyPaint, bulletPaint;

    public CrimsonGameThread(CrimsonGameView gameView, SurfaceHolder holder) {
        this.gameView = gameView;
        this.holder = holder;
        running = true;
        units = new ConcurrentLinkedDeque<>();
        heroPaint = new Paint();
        enemyPaint = new Paint();
        bulletPaint = new Paint();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void addBullet(Bullet bullet) {
        units.add(bullet);
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long eT = startTime;
        units.add(new Hero(gameView, heroPaint));
        while (running) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - eT > 500) {
                eT = currentTime;
                units.add(new Enemy(gameView, enemyPaint));
                Log.d("mike", "enemy add");
            }
            if (currentTime - startTime > 20) {
                startTime = currentTime;
                synchronized (holder) {
                    Canvas canvas = holder.lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    for (GameUnit unit : units) {
                        unit.draw(canvas);
                        if (!unit.isValid()) {
                            units.remove(unit);
                        }
                    }
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
