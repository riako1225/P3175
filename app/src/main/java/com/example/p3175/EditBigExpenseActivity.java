package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditBigExpenseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_big_expense);

        //region 0. VIEW

        EditText editTextAmount = findViewById(R.id.editTextEditBigExpenseAmount);
        EditText editTextDescription = findViewById(R.id.editTextEditBigExpenseDescription);
        Button buttonOK = findViewById(R.id.buttonEditBigExpenseOK);
        //endregion

        //region 1. FILL DATA OF ITEM BEING EDITED
        //endregion

        //region 2. VALIDATE INPUT
        //endregion

        //region 3. BUTTON
        //endregion
    }
}