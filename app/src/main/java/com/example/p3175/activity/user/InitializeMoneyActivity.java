package com.example.p3175.activity.user;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class InitializeMoneyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize_user);

        //region 0. VIEW

        EditText editTextCurrentSavings = findViewById(R.id.editTextCurrentSavings);
        Button buttonSalaryBill = findViewById(R.id.buttonFirstTimeManageSalary);
        Button buttonOK = findViewById(R.id.buttonInputInformationToMain);
        //endregion

        //region 1. BUTTON
        buttonSalaryBill.setOnClickListener(v -> {
            // nav to manage bill

        });

        buttonOK.setOnClickListener(v -> {
            // nav to main activity

        });
        //endregion
    }
}