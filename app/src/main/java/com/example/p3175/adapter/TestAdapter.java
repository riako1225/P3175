package com.example.p3175.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3175.R;

import java.util.List;

public class TestAdapter extends ListAdapter<List<String>, TestAdapter.TestViewHolder> {
    int layoutId;

    public TestAdapter(int layoutId){
        super(new DiffUtil.ItemCallback<List<String>>() {
            @Override
            public boolean areItemsTheSame(@NonNull List<String> oldItem, @NonNull List<String> newItem) {
                return oldItem.get(0).equals(newItem.get(0));
            }

            @Override
            public boolean areContentsTheSame(@NonNull List<String> oldItem, @NonNull List<String> newItem) {
                return oldItem.equals(newItem);
            }
        });

        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(layoutId, parent, false);

        return new TestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        List<String> item = getItem(position);

        holder.textView1.setText(item.get(1));
//        holder.textView2.setText(item.get(2));
    }

    class TestViewHolder extends RecyclerView.ViewHolder{
        TextView textView1, textView2, textView3;


        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }
}
