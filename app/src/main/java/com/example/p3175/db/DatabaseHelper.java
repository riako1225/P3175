package com.example.p3175.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.p3175.util.Converter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
@SuppressLint("DefaultLocale")
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;

    private static SQLiteDatabase writableDB;
    private static SQLiteDatabase readableDB;

    private static final String DB_NAME = "p3175_database"; // can change this name
    private static int DB_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String TABLE_OVERVIEW = "overview";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_TRANSACTION = "`transaction`";
    public static final String TABLE_RECURRING_TRANSACTION = "recurring_transaction";
    public static final String TABLE_BIG_EXPENSE = "big_expense";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_CATEGORY_ID = "category_id";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DAY_OF_MONTH = "day_of_month";

    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String COLUMN_OVERVIEW_TODAY_REMAINING = "today_remaining";
    public static final String COLUMN_OVERVIEW_TODAY_ALLOWED = "today_allowed";
    public static final String COLUMN_OVERVIEW_INCOMES = "incomes";
    public static final String COLUMN_OVERVIEW_SAVINGS = "savings";

    public static final String COLUMN_CATEGORY_IS_INCOME = "is_income";

    public static final String COLUMN_BIG_EXPENSE_INCOMES_NEEDED = "incomes_needed";
    public static final String COLUMN_BIG_EXPENSE_SAVINGS_NEEDED = "savings_needed";
    public static final String COLUMN_BIG_EXPENSE_LOAN_NEEDED = "loan_needed";


    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        readableDB = getReadableDatabase();
        writableDB = getWritableDatabase();
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
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
        onCreate(db);
    }

    //region COMMON METHODS

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
     * Select records with a given user_id in descending order
     */
    public Cursor listByUserId(String table, int userId) {
        String query = String.format("SELECT * FROM %s WHERE user_id = %d ORDER BY id DESC", table, userId);
        return readableDB.rawQuery(query, null);
    }

    /**
     * Delete a record by id
     *
     * @return number of deleted rows
     */
    public int delete(String table, int id) {
        return writableDB.delete(table, "id = ?", Converter.toArgs(id));
    }
    //endregion

    //region INSERT & UPDATE SPECIFIC TABLES

    public long insertUser(String email, String password) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        return writableDB.insert(TABLE_USER, null, values);
    }

    public int updateUser(int id, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, username);
        values.put(COLUMN_USER_PASSWORD, password);
        return writableDB.update(TABLE_USER, values, "id = ?", Converter.toArgs(id));
    }

    public long insertOverview(int userId, BigDecimal incomes, BigDecimal savings, BigDecimal todayAllowed, BigDecimal todayRemaining) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_OVERVIEW_INCOMES, Converter.fromBigDecimal(incomes));
        values.put(COLUMN_OVERVIEW_SAVINGS, Converter.fromBigDecimal(savings));
        values.put(COLUMN_OVERVIEW_TODAY_ALLOWED, Converter.fromBigDecimal(todayAllowed));
        values.put(COLUMN_OVERVIEW_TODAY_REMAINING, Converter.fromBigDecimal(todayRemaining));
        return writableDB.insert(TABLE_OVERVIEW, null, values);
    }

    public int updateOverview(int id, int userId, BigDecimal incomes, BigDecimal savings, BigDecimal todayAllowed, BigDecimal todayRemaining) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_OVERVIEW_INCOMES, Converter.fromBigDecimal(incomes));
        values.put(COLUMN_OVERVIEW_SAVINGS, Converter.fromBigDecimal(savings));
        values.put(COLUMN_OVERVIEW_TODAY_ALLOWED, Converter.fromBigDecimal(todayAllowed));
        values.put(COLUMN_OVERVIEW_TODAY_REMAINING, Converter.fromBigDecimal(todayRemaining));
        return writableDB.update(TABLE_OVERVIEW, values, "id = ?", Converter.toArgs(id));
    }

    public long insertCategory(String name, boolean isIncome) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CATEGORY_IS_INCOME, isIncome ? 1 : 0);
        return writableDB.insert(TABLE_CATEGORY, null, values);
    }

    public int updateCategory(int id, String name, boolean isIncome) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CATEGORY_IS_INCOME, isIncome ? 1 : 0);
        return writableDB.update(TABLE_CATEGORY, values, "id = ?", Converter.toArgs(id));
    }

    public long insertTransaction(int userId, int categoryId, BigDecimal amount, LocalDate date, String description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_CATEGORY_ID, categoryId);
        values.put(COLUMN_AMOUNT, Converter.fromBigDecimal(amount));
        values.put(COLUMN_DATE, Converter.fromLocalDate(date));
        values.put(COLUMN_DESCRIPTION, description);
        return writableDB.insert(TABLE_TRANSACTION, null, values);
    }

    public int updateTransaction(int id, int userId, int categoryId, BigDecimal amount, LocalDate date, String description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_CATEGORY_ID, categoryId);
        values.put(COLUMN_AMOUNT, Converter.fromBigDecimal(amount));
        values.put(COLUMN_DATE, Converter.fromLocalDate(date));
        values.put(COLUMN_DESCRIPTION, description);
        return writableDB.update(TABLE_TRANSACTION, values, "id = ?", Converter.toArgs(id));
    }

    public long insertRecurringTransaction(int userId, int categoryId, BigDecimal amount, int dayOfMonth, String description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_CATEGORY_ID, categoryId);
        values.put(COLUMN_AMOUNT, Converter.fromBigDecimal(amount));
        values.put(COLUMN_DAY_OF_MONTH, dayOfMonth);
        values.put(COLUMN_DESCRIPTION, description);
        return writableDB.insert(TABLE_RECURRING_TRANSACTION, null, values);
    }

    public int updateRecurringTransaction(int id, int userId, int categoryId, BigDecimal amount, int dayOfMonth, String description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_CATEGORY_ID, categoryId);
        values.put(COLUMN_AMOUNT, Converter.fromBigDecimal(amount));
        values.put(COLUMN_DAY_OF_MONTH, dayOfMonth);
        values.put(COLUMN_DESCRIPTION, description);
        return writableDB.update(TABLE_RECURRING_TRANSACTION, values, "id = ?", Converter.toArgs(id));
    }

    public long insertBigExpense(
            int userId, BigDecimal amount, LocalDate date, String description,
            BigDecimal incomesNeeded, BigDecimal savingsNeeded, BigDecimal loanNeeded){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_AMOUNT, Converter.fromBigDecimal(amount));
        values.put(COLUMN_DATE, Converter.fromLocalDate(date));
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_BIG_EXPENSE_INCOMES_NEEDED, Converter.fromBigDecimal(incomesNeeded));
        values.put(COLUMN_BIG_EXPENSE_SAVINGS_NEEDED, Converter.fromBigDecimal(savingsNeeded));
        values.put(COLUMN_BIG_EXPENSE_LOAN_NEEDED, Converter.fromBigDecimal(loanNeeded));
        return writableDB.insert(TABLE_BIG_EXPENSE, null, values);
    }

    public int updateBigExpense(
            int id, int userId, BigDecimal amount, LocalDate date, String description,
            BigDecimal incomesNeeded, BigDecimal savingsNeeded, BigDecimal loanNeeded
    ){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_AMOUNT, Converter.fromBigDecimal(amount));
        values.put(COLUMN_DATE, Converter.fromLocalDate(date));
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_BIG_EXPENSE_INCOMES_NEEDED, Converter.fromBigDecimal(incomesNeeded));
        values.put(COLUMN_BIG_EXPENSE_SAVINGS_NEEDED, Converter.fromBigDecimal(savingsNeeded));
        values.put(COLUMN_BIG_EXPENSE_LOAN_NEEDED, Converter.fromBigDecimal(loanNeeded));
        return writableDB.update(TABLE_BIG_EXPENSE, values, "id = ?", Converter.toArgs(id));
    }
    //endregion

    //region SPECIFIC METHODS

    /**
     * Select a user with given username
     */
    public Cursor selectUserByEmail(String username) {
        String query = String.format("SELECT * FROM user WHERE email = %s", username);
        return readableDB.rawQuery(query, null);
    }

    /**
     * Select a user with given username and password
     */
    public Cursor selectUserByEmailPassword(String email, String password) {
        String query = String.format("SELECT * FROM user WHERE email = %s AND password = %s", email, password);
        return readableDB.rawQuery(query, null);
    }


    /**
     * Select all income / expense categories
     */
    public Cursor listCategories(boolean isIncome) {
        String query;
        if (isIncome) {
            query = "SELECT * FROM category WHERE is_income = 1";
        } else {
            query = "SELECT * FROM category WHERE is_income = 0";
        }
        return readableDB.rawQuery(query, null);
    }

    /**
     * Select a user's all transaction during a given month
     */
    public Cursor listByUserIdYearMonth(int userId, String yearMonth) {
        String query = String.format(
                "SELECT * FROM transaction WHERE user_id = %d AND date LIKE %s || '%'", userId, yearMonth);
        return readableDB.rawQuery(query, null);
    }

    /**
     * Select all recurring income / expense transactions by user_id
     */
    public Cursor listRecurringTransactionsByUserId(int userId, boolean isIncome) {
        String query;
        if (isIncome) {
            query = String.format(
                    "SELECT * FROM recurring_transaction WHERE user_id = %d AND amount > 0 ORDER BY date DESC", userId);
        } else {
            query = String.format(
                    "SELECT * FROM recurring_transaction WHERE user_id = %d AND amount < 0 ORDER BY date DESC", userId);
        }
        return readableDB.rawQuery(query, null);
    }

    /**
     * Select all recurring transactions on a given day of month
     */
    public Cursor listRecurringTransactionsByUserIdDate(int userId, int dayOfMonth) {
        String query = String.format(
                "SELECT * FROM recurring_transaction WHERE user_id = %d AND day_of_month = %d", userId, dayOfMonth);
        return readableDB.rawQuery(query, null);
    }
    //endregion

    //region OTHERS

    /**
     * Create tables
     */
    private void createTables(SQLiteDatabase db) {
        List<String> queries = new ArrayList<>();
        queries.add(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s TEXT," +
                        "%s TEXT)",
                TABLE_USER,
                COLUMN_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD));

        queries.add(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER)",
                TABLE_OVERVIEW,
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_OVERVIEW_INCOMES,
                COLUMN_OVERVIEW_SAVINGS,
                COLUMN_OVERVIEW_TODAY_ALLOWED,
                COLUMN_OVERVIEW_TODAY_REMAINING));

        queries.add(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s TEXT," +
                        "%s INTEGER)",
                TABLE_CATEGORY,
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_CATEGORY_IS_INCOME));

        queries.add(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s TEXT," +    // LocalDate converted to TEXT
                        "%s TEXT)",
                TABLE_TRANSACTION,
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_CATEGORY_ID,
                COLUMN_AMOUNT,
                COLUMN_DATE,
                COLUMN_DESCRIPTION));

        queries.add(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s TEXT)",
                TABLE_RECURRING_TRANSACTION,
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_CATEGORY_ID,
                COLUMN_AMOUNT,
                COLUMN_DAY_OF_MONTH,            // int
                COLUMN_DESCRIPTION));

        queries.add(String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s INTEGER," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s INTEGER)",
                TABLE_BIG_EXPENSE,
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_DATE,            // LocalDate to TEXT
                COLUMN_DESCRIPTION,
                COLUMN_BIG_EXPENSE_INCOMES_NEEDED,
                COLUMN_BIG_EXPENSE_SAVINGS_NEEDED,
                COLUMN_BIG_EXPENSE_LOAN_NEEDED));

        for (String query : queries) {
            db.execSQL(query);
        }
    }

    /**
     * Insert initial data
     */
    private void fillInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_EMAIL, "admin");
        values.put(COLUMN_USER_PASSWORD, "admin");
        db.insert(TABLE_USER, null, values);
        values.clear();

        values.put(COLUMN_USER_EMAIL, "test");
        values.put(COLUMN_USER_PASSWORD, "test");
        db.insert(TABLE_USER, null, values);
        values.clear();

        values.put(COLUMN_NAME, "Expense 1");
        values.put(COLUMN_CATEGORY_IS_INCOME, 0);
        db.insert(TABLE_CATEGORY, null, values);
        values.clear();

    }

    /**
     * Drop all tables then recreate
     */
    private void doDestructiveMigration(SQLiteDatabase db) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_USER));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_OVERVIEW));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_CATEGORY));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_TRANSACTION));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_RECURRING_TRANSACTION));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_BIG_EXPENSE));
        createTables(db);
    }
    //endregion
}
