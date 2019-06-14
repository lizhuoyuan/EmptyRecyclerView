package com.example.zhuoy.androirtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            list.add("title" + i);
        }
    }

    private void initView() {
        EmptyRecyclerView emptyRecyclerView = findViewById(R.id.recycler_view);
        TextView tv = findViewById(R.id.remove_text);
        View emptyTextView = findViewById(R.id.add_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                myAdapter.notifyDataSetChanged();
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        emptyRecyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter();
        emptyRecyclerView.setAdapter(myAdapter);
        View emptyView = findViewById(R.id.empty);
        emptyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                initData();
                myAdapter.notifyDataSetChanged();
            }
        });
        emptyRecyclerView.setEmptyView(emptyView);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH vh, int i) {
            vh.title.setText(list.get(i));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class VH extends RecyclerView.ViewHolder {
            private final TextView title;

            VH(View v) {
                super(v);
                title = v.findViewById(R.id.title);
            }
        }
    }
}
