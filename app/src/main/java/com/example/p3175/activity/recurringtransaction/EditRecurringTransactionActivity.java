package com.example.p3175.activity.recurringtransaction;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditRecurringTransactionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recurring_transaction);

        //region 0. VIEW

        EditText editTextSalaryAmount = findViewById(R.id.editTextEditRecurringTransactionAmount);
        EditText editTextSalaryDate = findViewById(R.id.editTextEditRecurringTransactionDate);
        EditText editTextSalaryDescription = findViewById(R.id.editTextEditRecurringTransactionDescription);
        Button buttonOK = findViewById(R.id.buttonEditRecurringTransactionOK);
        RadioButton radioButtonIsSalary = findViewById(R.id.radioButtonIsSalary);
        RadioButton radioButtonIsBill = findViewById(R.id.radioButtonIsBill);

        radioButtonIsSalary.setChecked(true);
        buttonOK.setEnabled(false);
        //endregion

        //region 1. FILL DATA OF ITEM BEING EDITED

        // db select

        // fill data to edit text

        //endregion

        //region 2. VALIDATE INPUT
        //endregion

        //region 3. BUTTON

        buttonOK.setOnClickListener(v -> {
            // db update

            // nav back
        });
        //endregion
    }


}