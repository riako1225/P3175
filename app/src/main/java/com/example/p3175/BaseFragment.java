package com.example.p3175;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {

    protected static FragmentActivity activity;
    protected static SharedPreferences preferences;
    protected static SharedPreferences.Editor editor;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = requireActivity();
        preferences = activity.getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
}