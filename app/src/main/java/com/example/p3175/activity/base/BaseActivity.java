package com.example.p3175.activity.base;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.p3175.R;
import com.example.p3175.db.DatabaseHelper;
import com.example.p3175.db.entity.Overview;
import com.example.p3175.db.entity.User;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class BaseActivity extends AppCompatActivity {

    protected static String TAG = "tttt";

    protected static DatabaseHelper db;
    protected static SharedPreferences preferences;
    protected static SharedPreferences.Editor editor;
    protected static InputMethodManager inputMethodManager;     // for showing & hiding keyboard

    protected int currentUserId;
    protected User currentUser;
    protected Overview currentOverview;

    // FIXME: currentUser & currentXXX

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = DatabaseHelper.getInstance(this);
        preferences = getSharedPreferences(getString(R.string.shared_preference_name), MODE_PRIVATE);
        editor = preferences.edit();
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        getContextualData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getContextualData();
    }

    private void getContextualData() {
        currentUserId = preferences.getInt(getString(R.string.logged_in_user_id), -1);
        if (currentUserId == -1 && getIntent() != null) {
            currentUserId = getIntent().getIntExtra(getString(R.string.current_user_id), -1);
        }
        currentUser = db.selectUser(currentUserId);
        currentOverview = db.selectOverviewByUserId(currentUserId);
    }

    @Deprecated
    protected void refreshList(Cursor cursor, ListAdapter<List<String>, ? extends RecyclerView.ViewHolder> adapter) {
        List<List<String>> list = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }
                list.add(row);
            }
        }
        adapter.submitList(list);
    }
}