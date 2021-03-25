package com.example.p3175.activity.category;

import androidx.annotation.RequiresApi;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.db.DatabaseHelper;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditCategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        //region 0. VIEW

        RadioButton radioButtonIsIncome = findViewById(R.id.radioButtonEditCategoryIsIncome);
        RadioButton radioButtonIsExpense = findViewById(R.id.radioButtonEditCategoryIsExpense);
        EditText editTextCategoryName = findViewById(R.id.editTextEditCategoryName);
        Button buttonOK = findViewById(R.id.buttonEditCategoryOK);
        //endregion

        //region 1. FILL DATA OF ITEM BEING EDITED

        // db select
        int categoryId = getIntent().getIntExtra("categoryId", -1);
        Cursor cursor = db.select(DatabaseHelper.TABLE_CATEGORY, categoryId);

        // fill data
        if (cursor.moveToNext()) {
            editTextCategoryName.setText(cursor.getString(1));
            if (cursor.getInt(2) == 1) {
                radioButtonIsIncome.setChecked(true);
            } else {
                radioButtonIsExpense.setChecked(true);
            }
        }
        //endregion

        //region 2. VALIDATE INPUT
        //endregion

        //region 3. BUTTON

        buttonOK.setOnClickListener(v -> {
            // db update
            db.updateCategory(categoryId, editTextCategoryName.getText().toString(), radioButtonIsIncome.isChecked());

            // nav back
            onBackPressed();
        });

        //endregion
    }

}