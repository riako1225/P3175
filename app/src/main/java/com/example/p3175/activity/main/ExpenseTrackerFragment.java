package com.example.p3175.activity.main;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p3175.activity.base.BaseFragment;
import com.example.p3175.R;
import com.example.p3175.adapter.TransactionAdapter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ExpenseTrackerFragment extends BaseFragment {
    TransactionAdapter adapter;

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

        TextView textViewTodayRemaining = activity.findViewById(R.id.textViewTodayRemainingAmount);
        TextView textViewTodayAllowed = activity.findViewById(R.id.textViewTodayAllowedAmount);
        TextView textViewSavings = activity.findViewById(R.id.textViewSavingsAmount);
        TextView textViewIncome = activity.findViewById(R.id.textViewIncomeAmount);
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewTransaction);
        //endregion

        //region 1. TOP HALF: MONEY NUMBERS

        //endregion

        //region 2. BOTTOM HALF: RECYCLER VIEW FOR LIST

        adapter = new TransactionAdapter(activity, db);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        //endregion


    }

    @Override
    public void onResume() {
        super.onResume();

        // refresh recycler view
        adapter.submitList(db.listTransactionsByUserId(currentUserId));
    }
}