package com.samsung.circlekiller.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.samsung.circlekiller.CrimsonGameView;

public class Hero extends GameUnit {

    public Hero(CrimsonGameView gameView, Paint paint) {
        super(gameView, paint);
        dx = dy = 0;
        radius = 200;
        x = gameView.getRight() / 2f;
        y = gameView.getBottom() / 2f;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
    }
}
