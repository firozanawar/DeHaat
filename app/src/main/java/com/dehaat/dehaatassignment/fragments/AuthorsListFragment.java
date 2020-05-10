package com.dehaat.dehaatassignment.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.activity.BooksDetailsActivity;
import com.dehaat.dehaatassignment.adapter.AuthorAdapter;
import com.dehaat.dehaatassignment.listener.IClickListener;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.Data;
import com.dehaat.dehaatassignment.util.Constant;
import com.dehaat.dehaatassignment.viewmodel.AuthorBookViewModel;

import java.util.List;

public class AuthorsListFragment extends Fragment implements IClickListener {

    private static final String TAG = "AuthorFragment";

    boolean mDualPane;
    int mCurCheckPosition = 0;
    private Context mContext;

    private AuthorBookViewModel mViewModel;

    RecyclerView recyclerView;
    AuthorAdapter adapter;
    List<Author> authorList;
    ProgressBar progress;

    public static AuthorsListFragment newInstance() {
        return new AuthorsListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.authors_list_fragment, container, false);
        progress = view.findViewById(R.id.progressbar_author);
        progress.setVisibility(View.VISIBLE);
        recyclerView = view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AuthorBookViewModel.class);

        mViewModel.authorList.observe(getActivity(), new Observer<Data>() {
            @Override
            public void onChanged(@Nullable Data data) {

                if (data.code == Constant.CODE_SUCCESS) {
                    adapter = new AuthorAdapter(getActivity(), data.authors, AuthorsListFragment.this);
                    recyclerView.setAdapter(adapter);
                    progress.setVisibility(View.GONE);
                } else {
                    Toast.makeText(mContext.getApplicationContext(), data.message, Toast.LENGTH_LONG).show();
                }
            }
        });
        mViewModel.getAuthors();
        mViewModel.currentSelected.observe(getActivity(), new Observer<Integer>() {

            @Override
            public void onChanged(Integer pos) {
                adapter.updateCurrent(pos);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        View detailsFrame = getActivity().findViewById(R.id.layout_book);
        mDualPane = detailsFrame != null
                && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            showDetails(mCurCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(Author author, int postion, int lastSelected) {
        showDetails(postion);
        mViewModel.setCurrentSelected(postion);
    }

    private void showDetails(int index) {
        mCurCheckPosition = index;

        if (mDualPane) {
            BookDetailsFragment details = BookDetailsFragment.newInstance(index);
            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.layout_book, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), BooksDetailsActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
