package com.samsung.circlekiller.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.samsung.circlekiller.CrimsonGameView;

import static java.lang.Math.sqrt;

public class Bullet extends GameUnit {

    public Bullet(CrimsonGameView gameView, Paint paint, float tx, float ty) {
        super(gameView, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        radius = 30;
        y = gameView.getBottom() / 2f;
        x = gameView.getRight() / 2f;
        speed = 20;
        float disX = tx- x;
        float disY = ty - y;
        float c = (float) sqrt(disX * disX + disY * disY);
        float sin = disY / c;
        float cos = disX / c;
        dx = speed * cos;
        dy = speed * sin;
    }

    @Override
    public boolean isValid() {
        return x > 0 && x < gameView.getRight()
                && y > 0 && y < gameView.getBottom();
    }
}
