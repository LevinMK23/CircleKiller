package com.samsung.circlekiller;

import android.graphics.Rect;
import android.util.Log;

public class Circle {

    private float x, y, r;
    private float dx, dy;
    private Rect window;

    public Circle(float x, float y) {
        this.x = x;
        this.y = y;
        r = 60;
        dx = (float) (5 - Math.random() * 10);
        dy = (float) (5 - Math.random() * 10);
    }

    public void setWindowRect(Rect rect) {
        window = rect;
    }

    public void addSpeed() {

        dx *= 1.2;
        dy *= 1.2;

        Log.d("mike", "dx = " + dx + ", dy = " + dy);

    }


    /**
     * Круги должна отталкиваться друг от друга
     * )(
     * */
    public boolean hasConflictWith(Circle circle) {
        // TODO: 23.01.2021 Написать реализацию
        return false;
    }

    public void update() {

        x += dx;
        y += dy;

        if (x + r > window.right || x - r < window.left) {
            dx *= -1;
        }
        if (y + r > window.bottom || y - r < window.top) {
            dy *= -1;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

}
