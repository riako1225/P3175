package com.example.p3175.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper instance;

    private static SQLiteDatabase writableDB;
    private static SQLiteDatabase readableDB;

    private static final String DB_NAME = "p3175_database"; // can change this name
    private static int DB_VERSION = 1;

    public static final String TABLE_USER = "user";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";


    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        readableDB = getReadableDatabase();
        writableDB = getWritableDatabase();
    }

    public static DbHelper getDatabaseHelper(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        fillInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        doDestructiveMigration(db);
    }

    //region METHODS SHARED BY ALL TABLES
    /**
     * Select a single record by id
     */
    public Cursor select(String table, int id) {
        String query = String.format("SELECT * FROM %s WHERE id = %d", table, id);
        return readableDB.rawQuery(query, null);
    }

    /**
     * Select all records in a table
     */
    public Cursor list(String table) {
        String query = String.format("SELECT * FROM %s", table);
        return readableDB.rawQuery(query, null);
    }

    /**
     * Delete a record by id
     *
     * @return number of deleted rows
     */
    public int delete(String table, int id) {
        return writableDB.delete(table, "id = ?", toArgs(id));
    }
    //endregion

    // METHODS FOR SPECIFIC TABLES
    public long insertUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        return writableDB.insert(TABLE_USER, null, values);
    }

    public int updateUser(int id, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        return writableDB.update(TABLE_USER, values, "id = ?", toArgs(id));
    }


    /**
     * Create tables
     */
    private void createTables(SQLiteDatabase db) {
        String queryCreateTableUser = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s TEXT," +
                        "%s TEXT)",
                TABLE_USER,
                COLUMN_ID,
                COLUMN_USER_USERNAME,
                COLUMN_USER_PASSWORD);

        db.execSQL(queryCreateTableUser);

    }

    /**
     * Insert initial data
     */
    private void fillInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_USERNAME, "admin");
        values.put(COLUMN_USER_PASSWORD, "admin");
        db.insert(TABLE_USER, null, values);

        values.put(COLUMN_USER_USERNAME, "test");
        values.put(COLUMN_USER_PASSWORD, "test");
        db.insert(TABLE_USER, null, values);
    }

    /**
     * Drop all tables then recreate
     */
    private void doDestructiveMigration(SQLiteDatabase db) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_USER));
        createTables(db);
    }

    /**
     * Convert a couple of Strings to String[] for argument
     */
    private String[] toArgs(String... strings) {
        return strings;
    }

    /**
     * Convert int to String[] for argument
     */
    private String[] toArgs(int id) {
        return new String[]{String.valueOf(id)};
    }
}
