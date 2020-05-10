package com.dehaat.dehaatassignment.listener;

import com.dehaat.dehaatassignment.model.Author;

public interface IClickListener {

    void onListItemClick(Author author, int postion, int lastSelected);
}
