package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateRecurringTransactionActivity extends BaseActivity {

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
    }
}