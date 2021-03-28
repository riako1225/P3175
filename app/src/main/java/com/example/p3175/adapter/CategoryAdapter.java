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
import com.example.p3175.db.entity.Category;

import java.util.List;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.CategoryViewHolder> {
    Activity activity;
    boolean isForTransaction;
    OnClickListener onClickListener;

    public CategoryAdapter(Activity activity, boolean isForTransaction) {
        super(new DiffUtil.ItemCallback<Category>() {
            @Override
            public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.equals(newItem);
            }
        });

        this.activity = activity;
        this.isForTransaction = isForTransaction;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // itemView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cell_category, parent, false);

        // viewHolder
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category user = getItem(position);

        holder.textViewCategoryName.setText(user.getName());

        holder.itemView.setOnClickListener(v -> {
            int categoryId = user.getId();
            Intent intent;
            if (isForTransaction) {
                intent = new Intent(activity, CreateTransactionActivity.class);
            } else {
                intent = new Intent(activity, EditCategoryActivity.class);
            }
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
