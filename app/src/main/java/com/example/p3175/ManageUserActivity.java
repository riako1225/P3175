package com.example.p3175;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.p3175.adapter.UserAdapter;
import com.example.p3175.db.DatabaseHelper;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ManageUserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        //region 0. VIEW

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);
        Button buttonCreateAccount = findViewById(R.id.buttonAdminCreateAccount);
        //endregion

        //region 1. SETUP RECYCLER VIEW FOR DATA LIST

        // setup recycler view
        UserAdapter adapter = new UserAdapter(R.layout.cell_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // get all users from db


        // refresh the list
        refreshList(DatabaseHelper.TABLE_USER, adapter);

        //endregion

        //region ?. SWIPE TO DELETE

        //endregion

        //region 2. CREATE USER BUTTON
        buttonCreateAccount.setOnClickListener(v -> {
            // nav to edit user activity

        });
        //endregion

    }

}