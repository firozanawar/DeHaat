package com.dehaat.dehaatassignment.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.AuthorWithBook;

import java.util.List;

@Dao
public interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Author> authors);

    @Query("SELECT * FROM author")
    public List<AuthorWithBook> getAllAuthor();
}
