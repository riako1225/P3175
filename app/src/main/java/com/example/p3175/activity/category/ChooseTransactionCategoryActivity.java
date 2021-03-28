package com.example.p3175.activity.category;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.adapter.CategoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChooseTransactionCategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);

        //region 0. VIEW

        RecyclerView recyclerViewIncome = findViewById(R.id.recyclerViewIncomeCategories);
        RecyclerView recyclerViewExpense = findViewById(R.id.recyclerViewExpenseCategories);
        FloatingActionButton buttonAdd = findViewById(R.id.floatingActionButtonToAddCategory);
        buttonAdd.setVisibility(View.GONE);
        //endregion

        //region 1. RECYCLER VIEW TO LIST CATEGORIES (INCOME / EXPENSE)

        // setup
        CategoryAdapter adapterIncome = new CategoryAdapter(this, true);
        CategoryAdapter adapterExpense = new CategoryAdapter(this, true);
        recyclerViewIncome.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExpense.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIncome.setAdapter(adapterIncome);
        recyclerViewExpense.setAdapter(adapterExpense);

        // db select &  refresh the list
        adapterIncome.submitList(db.listCategories(true));
        adapterExpense.submitList(db.listCategories(false));
        //endregion

    }
}