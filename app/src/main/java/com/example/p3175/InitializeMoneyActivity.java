package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        //region 1. VALIDATE INPUT
        //endregion

        //region 2. BUTTON
        //endregion
    }
}