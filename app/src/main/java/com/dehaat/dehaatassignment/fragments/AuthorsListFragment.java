package com.dehaat.dehaatassignment.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.viewmodel.AuthorsBookViewModel;

public class AuthorsListFragment extends Fragment {

    private AuthorsBookViewModel mViewModel;

    public static AuthorsListFragment newInstance() {
        return new AuthorsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.authors_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AuthorsBookViewModel.class);
        // TODO: Use the ViewModel
    }

}
