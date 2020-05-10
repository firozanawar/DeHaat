package com.dehaat.dehaatassignment.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AuthorWithBook {
    @Embedded
    public Author author;

    @Relation(parentColumn = "author_id", entityColumn = "bookAuthorId")
    public List<Book> books;


}
