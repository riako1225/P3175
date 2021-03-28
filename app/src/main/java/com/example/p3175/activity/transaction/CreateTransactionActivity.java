package com.example.p3175.activity.transaction;

import androidx.annotation.RequiresApi;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.db.entity.Transaction;
import com.example.p3175.util.Converter;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateTransactionActivity extends BaseActivity {
    LocalDate datePickerDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);

        //region 0. VIEW

        EditText editTextAmount = findViewById(R.id.editTextEditTransactionAmount);
        EditText editTextDate = findViewById(R.id.editTextEditTransactionDate);
        EditText editTextDescription = findViewById(R.id.editTextEditTransactionDescription);
        EditText editTextCategory = findViewById(R.id.editTextEditTransactionCategoryName);
        ImageButton buttonDatePicker = findViewById(R.id.imageButtonDatePicker);
        Button buttonOK = findViewById(R.id.buttonEditTransactionOK);

        editTextCategory.setEnabled(false);
        editTextDate.setEnabled(false);
        buttonOK.setEnabled(false);
        //endregion

        //region 1. VALIDATE INPUT

        editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonOK.setEnabled(!editTextAmount.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //endregion

        //region 2. DATE PICKER

        // set to today by default
        datePickerDate = LocalDate.now();
        editTextDate.setText(datePickerDate.toString());    // set edit text

        // date picker button
        buttonDatePicker.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                datePickerDate = LocalDate.of(year, month + 1, dayOfMonth);
                editTextDate.setText(datePickerDate.toString());
            }, datePickerDate.getYear(), datePickerDate.getMonthValue() - 1, datePickerDate.getDayOfMonth());
            datePickerDialog.show();
        });

        //endregion

        //region 3. BUTTON

        buttonOK.setOnClickListener(v -> {
            // db insert
            db.insertTransaction(new Transaction(
                    currentUserId,
                    getIntent().getIntExtra("categoryId", -1),
                    Converter.stringToBigDecimal(editTextAmount.getText().toString()),
                    Converter.stringToLocalDate(editTextDate.getText().toString()),
                    editTextDescription.getText().toString()));

            // nav back
            onBackPressed();
        });
        //endregion

    }
}