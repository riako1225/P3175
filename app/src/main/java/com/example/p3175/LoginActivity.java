package com.example.p3175;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

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

        // get "logged_in_user" from shared pref
        // TODO: 3/24/2021
        // nav to main activity
        // TODO: 3/24/2021
        //endregion

        //region 2. LOGIN BUTTON

        buttonLogin.setOnClickListener(v -> {

            // gather input

            // try to retrieve a user from db by gathered username & password

            // if didn't retrieve a valid user, login fails. otherwise nav to other pages
            if (false) {  // FIXME: user not exists
                // login failed, show a msg

                Toast.makeText(this, "Invalid email / password.", Toast.LENGTH_SHORT).show();
            } else {
                // login successful

                if (true) {  // FIXME: is admin
                    // as admin, nav to admin page
                    // TODO

                } else {
                    // as user
                    // get user id from retrieved db record
                    // TODO

                    // nav to different pages depending on marks in shared pref
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
        buttonLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}