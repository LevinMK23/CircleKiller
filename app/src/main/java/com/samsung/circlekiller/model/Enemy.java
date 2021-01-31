package com.samsung.circlekiller.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.samsung.circlekiller.CrimsonGameView;

import java.io.File;
import java.lang.reflect.Field;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;

public class Enemy extends GameUnit {

    public void getFileInfo(String fileName){
// necessary code
        File file = new File(fileName);
        System.out.println(file.length() + " " + file.getAbsolutePath());
    }

    class NumberPhone {

        String num;

        public NumberPhone(String num) {
            this.num = num;
        }

        public boolean validate() {
            return num.matches("\\+7[0-9]{10}");
        }

    }

    public Enemy(CrimsonGameView gameView, Paint paint) {
        super(gameView, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        y = (float) (-100 - random() * 300);
        x = (float) (random() * gameView.getRight() * 1.5);
        radius = 60;
        speed = 10;
        float disX = gameView.getRight() / 2f - x;
        float disY = gameView.getBottom() / 2f - y;

        float c = (float) sqrt(disX * disX + disY * disY);
        float sin = disY / c;
        float cos = disX / c;
        dx = speed * cos;
        dy = speed * sin;
        // cosA = b / c
        // sinA = a / c
    }

    @Override
    public boolean isValid() {
        return y < gameView.getBottom();
    }
}
