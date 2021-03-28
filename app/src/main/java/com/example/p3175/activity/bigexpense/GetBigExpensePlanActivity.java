package com.example.p3175.activity.bigexpense;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class GetBigExpensePlanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_big_expense_plan);

        //region 0. VIEW

        EditText editTextAmount = findViewById(R.id.editTextEditBigExpenseAmount);
        EditText editTextDescription = findViewById(R.id.editTextEditBigExpenseDescription);
        Button buttonOK = findViewById(R.id.buttonEditBigExpenseOK);
        //endregion

    }
}