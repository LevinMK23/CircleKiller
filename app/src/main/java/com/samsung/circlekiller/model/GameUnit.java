package com.samsung.circlekiller.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.samsung.circlekiller.CrimsonGameView;

public class GameUnit {

    protected float x, y, radius;
    protected CrimsonGameView gameView;
    protected Paint paint;
    protected float dx, dy;
    protected float speed;

    public GameUnit(CrimsonGameView gameView, Paint paint) {
        this.gameView = gameView;
        this.paint = paint;
    }

    public boolean isValid() {
        return true;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
        update();
    }

    public void update() {
        x += dx;
        y += dy;
    }
}
