package com.samsung.circlekiller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView num;
    private Button click;
    private Button toNext;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.num);
        click = findViewById(R.id.click);
        toNext = findViewById(R.id.toNext);
        toNext.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ImageActivity.class);
            startActivity(intent);
        });
        preferences = getPreferences(Context.MODE_PRIVATE);
        loadHistoryValue();
        click.setOnClickListener(v -> updateNum(getIntValue() + 1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveHistory();
    }

    private void loadHistoryValue() {
        int value = preferences.getInt("value", 0);
        updateNum(value);
        Log.d("mike", "Loaded value = " + value + " from preference");
    }

    private void saveHistory() {
        preferences.edit()
                .putInt("value", getIntValue())
                .apply();
        Log.d("mike", "Saved value = " + getIntValue() + " to preference");

    }

    private int getIntValue() {
        return Integer.parseInt(num.getText().toString());
    }

    private void updateNum(int value) {
        num.setText(String.valueOf(value));
    }
}