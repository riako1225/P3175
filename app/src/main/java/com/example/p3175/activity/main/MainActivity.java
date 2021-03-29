package com.example.p3175.activity.main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.p3175.R;
import com.example.p3175.activity.base.BaseActivity;
import com.example.p3175.activity.bigexpense.CreateBigExpenseActivity;
import com.example.p3175.activity.bigexpense.EditBigExpenseActivity;
import com.example.p3175.activity.category.ChooseTransactionCategoryActivity;
import com.example.p3175.activity.category.ManageCategoryActivity;
import com.example.p3175.activity.overview.AddSavingsActivity;
import com.example.p3175.activity.recurringtransaction.ManageRecurringTransactionActivity;
import com.example.p3175.activity.report.ReportActivity;
import com.example.p3175.activity.user.EditUserActivity;
import com.example.p3175.activity.user.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 1. BOTTOM NAVIGATION BAR

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragmentBottomNavBar);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();

        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            // disable reselect current tab
        });
        //endregion

        //region 2. FLOATING BUTTON

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            int currentFragmentId = Objects.requireNonNull(navController.getCurrentDestination()).getId();

            if (currentFragmentId == R.id.expenseTrackerFragment) {

                // nav to category activity for adding transaction
                startActivity(new Intent(this, ChooseTransactionCategoryActivity.class));

            } else if (currentFragmentId == R.id.bigExpensePlannerFragment) {

                // nav to edit big expense activity for adding big expense
                startActivity(new Intent(this, CreateBigExpenseActivity.class));
            }
        });
        // endregion
    }

    // region 3. TOP RIGHT MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menuItemCategory) {
            startActivity(new Intent(this, ManageCategoryActivity.class));
        } else if (itemId == R.id.menuItemSalaryBill) {
            startActivity(new Intent(this, ManageRecurringTransactionActivity.class));
        } else if (itemId == R.id.menuItemAddSavings) {
            startActivity(new Intent(this, AddSavingsActivity.class));
        } else if (itemId == R.id.menuItemReport) {
            startActivity(new Intent(this, ReportActivity.class));
        } else if (itemId == R.id.menuItemAccount) {
            startActivity(new Intent(this, EditUserActivity.class));
        } else if (itemId == R.id.menuItemLogout) {
            // remove logged in tag in the shared pref
            editor.remove(getString(R.string.logged_in_user_id)).apply();

            // nav to login activity, unable to nav back
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion


    @Override
    public void onBackPressed() {
        finish();
    }
}