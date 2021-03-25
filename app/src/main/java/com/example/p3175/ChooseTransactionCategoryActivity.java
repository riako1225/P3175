package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChooseTransactionCategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        //region 0. VIEW

        RecyclerView recyclerViewIncomes = findViewById(R.id.recyclerViewIncomeCategories);
        RecyclerView recyclerViewExpenses = findViewById(R.id.recyclerViewExpenseCategories);
        //endregion

        //region 1. RECYCLER VIEW TO LIST CATEGORIES (INCOME / EXPENSE)

        //endregion

    }
}