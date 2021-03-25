package com.example.p3175.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3175.activity.transaction.CreateTransactionActivity;
import com.example.p3175.activity.category.EditCategoryActivity;
import com.example.p3175.R;

import java.util.List;

public class CategoryAdapter extends ListAdapter<List<String>, CategoryAdapter.CategoryViewHolder> {
    Activity activity;
    int layoutId;
    boolean isForTransaction;
    OnClickListener onClickListener;

    public CategoryAdapter(Activity activity, int layoutId, boolean isForTransaction) {
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

        this.activity = activity;
        this.layoutId = layoutId;
        this.isForTransaction = isForTransaction;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // itemView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(layoutId, parent, false);

        // viewHolder
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        List<String> item = getItem(position);

        holder.textViewCategoryName.setText(item.get(1));   // name

        // click navigation: add a transaction / edit this category
//        holder.itemView.setOnClickListener(v -> {
//            onClickListener.onClick(v, );
//        });


        holder.itemView.setOnClickListener(v -> {
            int categoryId = Integer.parseInt(item.get(0));
            Intent intent;
            if (isForTransaction) {
                intent = new Intent(activity, CreateTransactionActivity.class);
            } else {
                intent = new Intent(activity, EditCategoryActivity.class);
            }
            Log.d("tttt", String.valueOf("onBindViewHolder: " + intent==null));

            intent.putExtra("categoryId", categoryId);
            activity.startActivity(intent);
        });
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCategoryName = itemView.findViewById(R.id.textViewCategoryName);
        }
    }
}
