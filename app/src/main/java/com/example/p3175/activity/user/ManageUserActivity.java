package com.example.p3175.activity.user;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.adapter.UserAdapter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ManageUserActivity extends BaseActivity {
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        //region 0. VIEW

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);
        Button buttonCreateAccount = findViewById(R.id.buttonAdminCreateAccount);
        //endregion

        //region 1. SETUP RECYCLER VIEW FOR DATA LIST

        // setup recycler view
        adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // get all users from db


        // refresh the list


        //endregion

        //region ?. SWIPE TO DELETE

        //endregion

        //region 2. BUTTON CREATE

        buttonCreateAccount.setOnClickListener(v -> {
            // nav to edit user activity

        });
        //endregion

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.submitList(db.listUsers());
    }
}