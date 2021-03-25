package com.example.p3175.activity.transaction;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateTransactionActivity extends BaseActivity {

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
        buttonOK.setEnabled(!TextUtils.isEmpty(editTextAmount.getText()));
        //endregion

        //region 1. VALIDATE INPUT
        //endregion

        //region 2. BUTTON
        buttonOK.setOnClickListener(v -> {
            // db insert

            // nav back
        });
        //endregion

    }
}