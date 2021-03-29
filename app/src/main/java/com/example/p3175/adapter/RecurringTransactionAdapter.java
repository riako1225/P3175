package com.example.p3175.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3175.R;
import com.example.p3175.activity.recurringtransaction.EditRecurringTransactionActivity;
import com.example.p3175.activity.transaction.EditTransactionActivity;
import com.example.p3175.db.entity.RecurringTransaction;
import com.example.p3175.util.Converter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RecurringTransactionAdapter extends ListAdapter<RecurringTransaction, RecurringTransactionAdapter.RecurringTransactionViewHolder> {
    Activity activity;

    public RecurringTransactionAdapter(Activity activity) {
        super(new DiffUtil.ItemCallback<RecurringTransaction>() {
            @Override
            public boolean areItemsTheSame(@NonNull RecurringTransaction oldItem, @NonNull RecurringTransaction newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull RecurringTransaction oldItem, @NonNull RecurringTransaction newItem) {
                return oldItem.equals(newItem);
            }
        });

        this.activity = activity;
    }

    @NonNull
    @Override
    public RecurringTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // itemView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cell_recurring_transaction, parent, false);

        // viewHolder
        return new RecurringTransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecurringTransactionViewHolder holder, int position) {
        RecurringTransaction recurringTransaction = getItem(position);



        // set text
        holder.textViewRecurringTransactionId.setText(String.valueOf(position + 1));
        holder.textViewRecurringTransactionDayOfMonth.setText(String.valueOf(recurringTransaction.getDayOfMonth()));
        holder.textViewRecurringTransactionDescription.setText(recurringTransaction.getDescription());
        holder.textViewRecurringTransactionAmount.setText(Converter.bigDecimalToString(recurringTransaction.getAmount()));


        // click to edit
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, EditRecurringTransactionActivity.class);
            intent.putExtra(activity.getString(R.string.recurring_transaction_id), recurringTransaction.getId());
            activity.startActivity(intent);
        });
    }

    static class RecurringTransactionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRecurringTransactionId,
                textViewRecurringTransactionDayOfMonth,
                textViewRecurringTransactionAmount,
                textViewRecurringTransactionDescription;


        public RecurringTransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRecurringTransactionId = itemView.findViewById(R.id.textViewRecurringTransactionId);
            textViewRecurringTransactionDayOfMonth = itemView.findViewById(R.id.textViewRecurringTransactionDate);
            textViewRecurringTransactionAmount = itemView.findViewById(R.id.textViewRecurringTransactionAmount);
            textViewRecurringTransactionDescription = itemView.findViewById(R.id.textViewRecurringTransactionDescription);
        }
    }
}
