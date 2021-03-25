package com.example.p3175;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ExpenseTrackerFragment extends Fragment {

       public ExpenseTrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_tracker, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //region 0. VIEW
        //endregion

        //region 1. TOP HALF: MONEY NUMBERS
        //endregion

        //region 2. BOTTOM HALF: RECYCLER VIEW FOR LIST
        //endregion


    }
}