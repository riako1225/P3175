package com.example.p3175.activity.user;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateUserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //region 0. VIEW

        TextView textViewTitle = findViewById(R.id.textViewEditUserTitle);
        EditText editTextEmail = findViewById(R.id.editTextEditUserEmail);
        EditText editTextPassword = findViewById(R.id.editTextEditUserPassword);
        EditText editTextVerifyPassword = findViewById(R.id.editTextEditUserVerifyPassword);
        EditText editTextOldPassword = findViewById(R.id.editTextEditUserOldPassword);
        Button buttonOK = findViewById(R.id.buttonEditUserOK);
        //endregion

        //region 1. VALIDATE INPUT

        //endregion

        //region 2. BUTTON

        buttonOK.setOnClickListener(v -> {

            // db select by email

            // if returned cursor can move next, this email exists, make a toast

            // else db insert

            // if previous activity is manage user activity (admin), mark "need change password" in shard pref

            // nav back, hide keyboard
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
        //endregion

    }
}