package com.example.p3175;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.p3175.adapter.TestAdapter;
import com.example.p3175.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DbDemoActivity extends AppCompatActivity {
    TestAdapter adapter;
    List<List<String>> list;

    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_demo);

        // get db helper
        databaseHelper = DatabaseHelper.getInstance(this);

        // set recycler view
        recyclerView = findViewById(R.id.recyclerViewTest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TestAdapter(R.layout.cell_test);
        recyclerView.setAdapter(adapter);

        // display the list
        list = new ArrayList<>();
        refreshList(DatabaseHelper.TABLE_USER);

        // swipe to delete
        Activity activity = this;
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(activity)
                        .setTitle("Delete?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            List<String> record = list.get(viewHolder.getAdapterPosition());
                            // delete from db
                            databaseHelper.delete(DatabaseHelper.TABLE_USER, Integer.parseInt(record.get(0)));

                            // refresh the list
                            refreshList(DatabaseHelper.TABLE_USER);

                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // not deleting, restore the list
                            adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                        })
                        .create()
                        .show();
            }
        }).attachToRecyclerView(recyclerView);

        // button: test add
        Button buttonAdd = findViewById(R.id.buttonInsert);
        buttonAdd.setOnClickListener(v -> {
            // insert to db
            databaseHelper.insertUser("3", "33");

            // refresh list
            refreshList(DatabaseHelper.TABLE_USER);
        });

        // button: test edit
        Button buttonEdit = findViewById(R.id.buttonUpdate);
        buttonEdit.setOnClickListener(v -> {
            // update where id = 3
            databaseHelper.updateUser(1, "123", "123123");

            // refresh list
            refreshList(DatabaseHelper.TABLE_USER);
        });
    }

    /**
     * Select all records from a table, convert to a list and display them
     */

    private void refreshList(String table) {
        list = new ArrayList<>();

        Cursor cursor = databaseHelper.list(table);
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
