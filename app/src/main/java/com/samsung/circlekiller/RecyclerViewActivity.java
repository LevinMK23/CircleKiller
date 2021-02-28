package com.samsung.circlekiller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private  ChatHistoryService historyService;
    protected List<String> chatText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        historyService = new ChatHistoryService(getApplicationContext());
        chatText = historyService.getHistory();
        EditText input = findViewById(R.id.input);
        Button send = findViewById(R.id.send);
        ChatAdapter adapter = new ChatAdapter(chatText, recyclerView);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        send.setOnClickListener(v -> {
            chatText.add(input.getText().toString());
            input.setText("");
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        historyService.saveHistory(chatText);
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatItemHolder> {

        private List<String> chatText;
        private RecyclerView recyclerView;

        public ChatAdapter(List<String> chatText, RecyclerView recyclerView) {
            this.chatText = chatText;
            this.recyclerView = recyclerView;
        }

        @NonNull
        @Override
        public ChatItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new ChatItemHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatItemHolder holder, int position) {
            holder.setText(chatText.get(position));
        }

        @Override
        public int getItemCount() {
            return chatText.size();
        }
    }

    private class ChatItemHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ChatItemHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            imageView.setImageResource(R.drawable.ava);
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}