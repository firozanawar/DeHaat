package com.dehaat.dehaatassignment.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dehaat.dehaatassignment.model.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Book> books);

    @Query("SELECT * FROM book")
    public List<Book> getAllBooks();
}
