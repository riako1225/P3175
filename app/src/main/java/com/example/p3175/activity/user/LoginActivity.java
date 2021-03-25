package com.example.p3175.activity.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.p3175.activity.main.MainActivity;
import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //region 0. VIEW

        EditText editTextEmail = findViewById(R.id.editTextLoginEmail);
        EditText editTextPassword = findViewById(R.id.editTextLoginPassword);
        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        //endregion

        //region 1. IF ALREADY LOGGED IN, NAV TO MAIN, UNABLE TO NAV BACK

        // TODO: 3/24/2021
        // get "logged_in_user" from shared pref

        // nav to main activity

        //endregion

        //region 2. LOGIN BUTTON

        buttonLogin.setOnClickListener(v -> {

            // try to retrieve a user from db by input email & password
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            // if didn't retrieve a valid user, login fails.
            if (false) {  // FIXME: user not exists
                // login failed, show a msg

                Toast.makeText(this, "Invalid email / password.", Toast.LENGTH_SHORT).show();
            } else {
                // login successful

                if (getResources().getString(R.string.admin_username).equals(email)) {
                    // as admin, nav to admin page

                } else {
                    // as user
                    // get user id from retrieved db record
                    currentUserId = -1;     // FIXME: user id

                    // save this user id into shared pref as logged in user id
                    editor.putInt(getResources().getString(R.string.logged_in_user_id), currentUserId);

                    // nav to different pages depending on flag in shared pref
                    if (true) {  // FIXME
                        // if this user needs to change password
                        // nav to first time login activity

                    } else {
                        // need to do nothing else
                        // nav to main activity directly

                        // hide the keyboard
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        });
        //endregion

        //region 3. CREATE ACCOUNT BUTTON

        textViewCreateAccount.setOnClickListener(v -> {
            // nav to edit user activity
            Intent intent = new Intent(this, EditUserActivity.class);
            startActivity(intent);

            // hide the keyboard
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
        //endregion

        // test
        Button test = findViewById(R.id.buttonTest);
        test.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        });
    }
}