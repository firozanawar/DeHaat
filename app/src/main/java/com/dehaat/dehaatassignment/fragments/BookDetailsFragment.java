package com.dehaat.dehaatassignment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.adapter.BookAdapter;
import com.dehaat.dehaatassignment.model.Book;
import com.dehaat.dehaatassignment.model.Data;
import com.dehaat.dehaatassignment.viewmodel.AuthorBookViewModel;

import java.util.List;

public class BookDetailsFragment extends Fragment {

    private AuthorBookViewModel mViewModel;

    RecyclerView recyclerView;
    BookAdapter adapter;
    List<Book> authorList;
    private int currentIndex;
    ProgressBar progress;

    public static BookDetailsFragment newInstance(int index) {
        BookDetailsFragment f = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_books);
        progress = view.findViewById(R.id.progressbar);
        progress.setVisibility(View.VISIBLE);
        Bundle bundle = getArguments();
        currentIndex = bundle.getInt("index");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AuthorBookViewModel.class);

        mViewModel.authorList.observe(getActivity(), new Observer<Data>() {
            @Override
            public void onChanged(@Nullable Data data) {
                adapter = new BookAdapter(getActivity(), data.authors.get(currentIndex).getBooks());
                recyclerView.setAdapter(adapter);
                progress.setVisibility(View.GONE);
            }
        });
        mViewModel.getAuthors();
        mViewModel.currentSelected.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //mViewModel.setLastSelected(currentIndex);
                currentIndex = integer;
            }
        });

        // setListAdapter here
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
