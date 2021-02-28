package com.samsung.circlekiller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryService {

    private SQLiteDatabase database;
    private Context context;

    public ChatHistoryService(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase("chat_history", Context.MODE_PRIVATE, null);
        database.execSQL("create table if not exists chat_history (chat_text text)");
    }

    public List<String> getHistory() {
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery("select * from chat_history", null);
        ArrayList<String> list = new ArrayList<>();
        cursor.moveToFirst();
        do {
            try {
                list.add(cursor.getString(cursor.getColumnIndex("chat_text")));
            } catch (Exception e) {
                Log.d("mike", "db select error");
            }
        } while (cursor.moveToNext());
        return list;
    }

    public void saveHistory(List<String> history) {
        database.execSQL("delete from chat_history");
        for (String s : history) {
            database.execSQL("insert into chat_history values('" + s + "')");
        }
    }
}
