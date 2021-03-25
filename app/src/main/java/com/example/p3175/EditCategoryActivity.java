package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
        //endregion

        //region 2. VALIDATE INPUT
        //endregion

        //region 3. BUTTON
        //endregion
    }

}