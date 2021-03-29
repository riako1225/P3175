package com.example.p3175.activity.user;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.db.entity.Overview;
import com.example.p3175.db.entity.User;
import com.example.p3175.util.Converter;

import java.math.BigDecimal;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateUserActivity extends BaseActivity {

    private boolean isEmailValid;
    private boolean isPasswordValid;

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
        editTextOldPassword.setVisibility(View.GONE);
        Button buttonOK = findViewById(R.id.buttonEditUserOK);
        buttonOK.setEnabled(false);
        //endregion

        //region 1. VALIDATE INPUT

        isEmailValid = false;
        isPasswordValid = false;

        // validate email
        editTextEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEmailValid = !editTextEmail.getText().toString().isEmpty();

                buttonOK.setEnabled(isEmailValid && isPasswordValid);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // validate password
        TextWatcher textWatcher = new TextWatcher() {
            String password, verifyPassword;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = editTextPassword.getText().toString();
                verifyPassword = editTextVerifyPassword.getText().toString();
                isPasswordValid = password.length() >= 4 && password.equals(verifyPassword);
                buttonOK.setEnabled(isEmailValid && isPasswordValid);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextPassword.addTextChangedListener(textWatcher);
        editTextVerifyPassword.addTextChangedListener(textWatcher);

        //endregion

        //region 2. BUTTON

        buttonOK.setOnClickListener(v -> {

            // check email exists or not
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (db.selectUserByEmail(email) != null) {
                // email exists

                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            } else {
                // email doesn't exist

                // db insert
                db.insertUser(new User(email, Converter.toMd5(password)));
                int userId = db.selectUserByEmail(email).getId();
                db.insertOverview(new Overview(userId, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

                // if previous activity is manage user activity (admin), mark "need change password" in shard pref
                if (getIntent().getBooleanExtra("isCreatedByAdmin", false)) {
                    editor.putBoolean(getString(R.string.need_change_password) + userId, true).apply();
                }

                // nav to initialize money activity, hide keyboard
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                Intent intent = new Intent(this, InitializeMoneyActivity.class);
                intent.putExtra(getString(R.string.current_user_id), userId);
                startActivity(intent);
            }


        });
        //endregion

    }
}