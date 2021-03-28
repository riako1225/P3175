package com.example.p3175.activity.recurringtransaction;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.adapter.RecurringTransactionAdapter;
import com.example.p3175.db.DatabaseHelper;
import com.example.p3175.db.entity.RecurringTransaction;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ManageRecurringTransactionActivity extends BaseActivity {

    RecurringTransactionAdapter adapterIncome, adapterBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_recurring_transaction);

        //region 0. VIEW

        RecyclerView recyclerViewIncome = findViewById(R.id.recyclerViewIncome);
        RecyclerView recyclerViewBill = findViewById(R.id.recycleViewBill);
        Button buttonCreate = findViewById(R.id.buttonManageRecurringTransactionAdd);
        //endregion

        //region 1. RECYCLER VIEW

        adapterIncome = new RecurringTransactionAdapter(this);
        adapterBill = new RecurringTransactionAdapter(this);
        recyclerViewIncome.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBill.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIncome.setAdapter(adapterIncome);
        recyclerViewBill.setAdapter(adapterBill);
        //endregion

        //region 2. SWIPE DELETE
        Context context = this;
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            RecurringTransaction itemToDelete = adapterIncome.getCurrentList().get(viewHolder.getAdapterPosition());
                            db.delete(DatabaseHelper.TABLE_RECURRING_TRANSACTION, itemToDelete.getId());
                        })
                        .setNegativeButton("No",
                                (dialog, which) -> adapterIncome.notifyItemChanged(viewHolder.getAdapterPosition()))
                        .create()
                        .show();
            }
        }).attachToRecyclerView(recyclerViewIncome);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            RecurringTransaction itemToDelete = adapterBill.getCurrentList().get(viewHolder.getAdapterPosition());
                            db.delete(DatabaseHelper.TABLE_RECURRING_TRANSACTION, itemToDelete.getId());
                        })
                        .setNegativeButton("No",
                                (dialog, which) -> adapterBill.notifyItemChanged(viewHolder.getAdapterPosition()))
                        .create()
                        .show();
            }
        }).attachToRecyclerView(recyclerViewBill);
        //endregion

        //region 3. BUTTON CREATE

        buttonCreate.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateRecurringTransactionActivity.class));
        });
        //endregion
    }

    @Override
    protected void onResume() {
        super.onResume();

        // refresh recycler view
        adapterIncome.submitList(db.listRecurringTransactionsByUserId(currentUserId, true));
        adapterBill.submitList(db.listRecurringTransactionsByUserId(currentUserId, false));
    }
}