package com.example.p3175;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 1. SETUP THE BOTTOM NAVIGATION BAR

        NavController navController = Navigation.findNavController(this, R.id.navHostFragmentBottomNavBar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();

        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //endregion

        //region 2. SETUP FLOATING BUTTON

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            int currentFragmentId = Objects.requireNonNull(navController.getCurrentDestination()).getId();

            if (currentFragmentId == R.id.expenseTrackerFragment) {

                // nav to category activity for adding transaction
                Intent intent = new Intent(this, CategoryActivity.class);
                intent.putExtra("isAddingTransaction", true);
                startActivity(intent);

            } else if (currentFragmentId == R.id.bigExpensePlannerFragment) {

                // nav to edit big expense activity for adding big expense
                startActivity(new Intent(this, EditBigExpenseActivity.class));
            }
        });
        // endregion


//            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);

    }

    // region 3. TOP RIGHT MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menuItemCategory) {
            startActivity(new Intent(this, CategoryActivity.class));
        } else if (itemId == R.id.menuItemSalaryBill) {
            startActivity(new Intent(this, RecurringTransactionActivity.class));
        } else if (itemId == R.id.menuItemAccount) {
            startActivity(new Intent(this, EditUserActivity.class));
        } else if (itemId == R.id.menuItemReport) {
            startActivity(new Intent(this, ReportActivity.class));
        } else if (itemId == R.id.menuItemLogout) {
            // TODO: 3/24/2021
            // remove logged in tag in the shared pref

            // nav to login activity

        }

        return super.onOptionsItemSelected(item);
    }
    //endregion
}