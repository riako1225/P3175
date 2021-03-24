package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //region 0. VIEW
        //endregion

        //region 1. RECYCLER VIEW
        //endregion

        //region 2. DETERMINE THIS ACTIVITY'S PURPOSE: ADD A TRANSACTION / MANAGE CATEGORIES
        // get argument from intent

        if (true) {

        } else {

        }
        //endregion

        //region 3. BUTTON ADD
        //endregion

        //region ?. SWIPE DELETE
        //endregion


    }
}