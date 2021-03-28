package com.example.p3175.activity.recurringtransaction;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.db.entity.RecurringTransaction;
import com.example.p3175.util.Converter;

import java.math.BigDecimal;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditRecurringTransactionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recurring_transaction);

        //region 0. VIEW

        EditText editTextAmount = findViewById(R.id.editTextEditRecurringTransactionAmount);
        EditText editTextDayOfMonth = findViewById(R.id.editTextEditRecurringTransactionDayOfMonth);
        EditText editTextDescription = findViewById(R.id.editTextEditRecurringTransactionDescription);
        Button buttonOK = findViewById(R.id.buttonEditRecurringTransactionOK);
        RadioButton radioButtonIsIncome = findViewById(R.id.radioButtonIsIncome);
        RadioButton radioButtonIsBill = findViewById(R.id.radioButtonIsBill);

        radioButtonIsIncome.setChecked(true);
        buttonOK.setEnabled(false);
        //endregion

        //region 1. VALIDATE INPUT

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonOK.setEnabled(!editTextAmount.getText().toString().isEmpty()
                        && !editTextDayOfMonth.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextAmount.addTextChangedListener(textWatcher);
        editTextDayOfMonth.addTextChangedListener(textWatcher);
        //endregion

        //region 2. FILL DATA OF ITEM BEING EDITED

        // db select
        RecurringTransaction recurringTransaction = db.selectRecurringTransaction(getIntent().getIntExtra("recurringTransactionId", -1));
        assert recurringTransaction != null;

        // fill data to edit text
        editTextAmount.setText(Converter.bigDecimalToString(recurringTransaction.getAmount()));
        editTextDayOfMonth.setText(String.valueOf(recurringTransaction.getDayOfMonth()));
        editTextDescription.setText(recurringTransaction.getDescription());
        //endregion


        //region 3. BUTTON

        buttonOK.setOnClickListener(v -> {
            // db update
            recurringTransaction.setAmount(radioButtonIsIncome.isChecked()
                    ? Converter.stringToBigDecimal(editTextAmount.getText().toString())
                    : Converter.stringToBigDecimal(editTextAmount.getText().toString()).negate());
            recurringTransaction.setDayOfMonth(Integer.parseInt(editTextDayOfMonth.getText().toString()));
            recurringTransaction.setDescription(editTextDescription.getText().toString());
            db.updateRecurringTransaction(recurringTransaction);

            // nav back
            onBackPressed();
        });
        //endregion
    }


}