package com.example.p3175;

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

import com.example.p3175.db.DatabaseHelper;

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

    // FIXME: currentUser & currentXXX

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = DatabaseHelper.getInstance(this);
        preferences = getPreferences(MODE_PRIVATE);
        editor = preferences.edit();
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        currentUserId = preferences.getInt(getResources().getString(R.string.logged_in_user_id), -1);

        // FIXME: try to get user by id
    }

    protected void refreshList(String table, ListAdapter<List<String>, ? extends RecyclerView.ViewHolder> adapter) {
        List<List<String>> list = new ArrayList<>();

        Cursor cursor = db.list(table);

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