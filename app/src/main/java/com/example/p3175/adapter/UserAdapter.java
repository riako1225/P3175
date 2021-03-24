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

public class UserAdapter extends ListAdapter<List<String>, UserAdapter.UserViewHolder> {
    int layoutId;

    public UserAdapter(int layoutId) {
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
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(layoutId, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        List<String> item = getItem(position);

        holder.textViewUserId.setText(String.valueOf(position + 1));    // position in the list
        holder.textViewUserEmail.setText(item.get(1));                  // email
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserId, textViewUserEmail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserId = itemView.findViewById(R.id.textViewUserId);
            textViewUserEmail = itemView.findViewById(R.id.textViewUserEmail);
        }

    }
}
