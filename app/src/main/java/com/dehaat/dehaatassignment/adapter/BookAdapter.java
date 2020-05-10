package com.dehaat.dehaatassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksViewHolder> {

    Context mCtx;
    List<Book> booksList;

    public BookAdapter(Context mCtx, List<Book> booksList) {
        this.mCtx = mCtx;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_layout_book, parent, false);
        return new BookAdapter.BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvPrice.setText(book.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {

        TextView tvBookTitle;
        TextView tvPrice;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBookTitle = itemView.findViewById(R.id.tv_bookTitle);
            tvPrice = itemView.findViewById(R.id.tv_Price);
        }
    }
}
