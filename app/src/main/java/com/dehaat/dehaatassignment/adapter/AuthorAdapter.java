package com.dehaat.dehaatassignment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.listener.IClickListener;
import com.dehaat.dehaatassignment.model.Author;

import java.util.ArrayList;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {

    private Context mCtx;
    private ArrayList<Author> authorList;
    private IClickListener mlistener;
    private int lastSelected = -1;

    public AuthorAdapter(Context mCtx, ArrayList<Author> authorList, IClickListener listener) {
        this.mCtx = mCtx;
        this.authorList = authorList;
        this.mlistener = listener;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_layout_author, parent, false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, final int position) {
        final Author author = authorList.get(position);
        holder.tvAuthorName.setText(author.getAuthor_name());
        holder.tvAuthorBio.setText(author.getAuthor_bio());

        holder.itemView.setOnClickListener(v -> {
            if (lastSelected != -1) {
                authorList.get(lastSelected).setSelected(false);
                notifyItemChanged(lastSelected);
            }
            mlistener.onListItemClick(author, position, lastSelected);
            lastSelected = position;
        });
        if (author.isSelected())
            holder.itemView.setBackgroundColor(Color.parseColor("#808080"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthorName;
        TextView tvAuthorBio;

        AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthorName = itemView.findViewById(R.id.tv_authorName);
            tvAuthorBio = itemView.findViewById(R.id.tv_author_bio);
        }
    }

    public void updateCurrent(int pos) {
        if (lastSelected == -1)
            lastSelected = pos;
        authorList.get(pos).setSelected(true);
        notifyItemChanged(pos);
    }
}
