package com.example.p3175.activity.bigexpense;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateBigExpenseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_big_expense);

        //region 0. VIEW

        EditText editTextAmount = findViewById(R.id.editTextEditBigExpenseAmount);
        EditText editTextDescription = findViewById(R.id.editTextEditBigExpenseDescription);
        Button buttonOK = findViewById(R.id.buttonEditBigExpenseOK);
        //endregion

        //region 1. VALIDATE INPUT

        //endregion

        //region 2. BUTTON
        buttonOK.setOnClickListener(v -> {
            // nav to get plan activity
        });
        //endregion
    }
}