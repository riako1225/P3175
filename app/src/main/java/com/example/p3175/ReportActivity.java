package com.example.p3175;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //region 0. VIEW

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        //endregion

        //region 1. SETUP VIEW PAGER

        // adapter
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                    default:
                        return new ReportTextFragment();
                    case 1:
                        return new ReportLineChartFragment();
                    case 2:
                        return new ReportPieChartFragment();
                }
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

        // mediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText(getResources().getString(R.string.tab_report_text));
                    break;
                case 1:
                    tab.setText(getResources().getString(R.string.tab_report_line_chart));
                    break;
                case 2:
                    tab.setText(getResources().getString(R.string.tab_report_pie_chart));
                    break;
            }
        }).attach();
        //endregion

    }
}