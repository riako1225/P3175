package com.example.p3175.activity.recurringtransaction;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ManageRecurringTransactionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recurring_transaction);

        //region 0. VIEW
        //endregion

        //region 1. RECYCLER VIEW
        //endregion

        //region 2. SWIPE DELETE
        //endregion
    }
}