package com.dehaat.dehaatassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dehaat.dehaatassignment.database.AppDatabase;
import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.AuthorWithBook;
import com.dehaat.dehaatassignment.model.Book;
import com.dehaat.dehaatassignment.model.Data;
import com.dehaat.dehaatassignment.rest.AppRestClient;
import com.dehaat.dehaatassignment.rest.AppRestClientService;
import com.dehaat.dehaatassignment.util.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorBookViewModel extends AndroidViewModel {

    public MutableLiveData<Data> authorList = new MutableLiveData<>();
    private Application application;
    private AppDatabase appDatabase;
    private static ScheduledExecutorService executor = null;

    public MutableLiveData<Integer> currentSelected = new MutableLiveData<>();

    public AuthorBookViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        appDatabase = AppDatabase.getDatabase(application.getApplicationContext());
        executor = Executors.newScheduledThreadPool(1);
    }

    public void getAuthors() {
        getData();
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadAuthors() {

        AppRestClientService appRestClient = AppRestClient.getInstance().getRestClient();
        Call<Data> call = appRestClient.getAuthors();

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                executor.execute(() -> {
                    Data data = response.body();
                    data.code = Constant.CODE_SUCCESS;
                    appDatabase.getAuthorDao().insertAll(data.authors);
                    for (int i = 0; i < data.authors.size(); i++) {
                        List<Book> books = data.authors.get(i).getBooks();
                        for (Book book : books) {
                            book.setBookAuthorId(data.authors.get(i).getAuthor_id());
                        }
                        appDatabase.getBookDao().insertAll(data.authors.get(i).getBooks());
                    }

                });

                if (response.body() != null) {
                    response.body().code = Constant.CODE_SUCCESS;
                    authorList.setValue(response.body());
                } else {
                    Data myResponse = new Data(Constant.CODE_ERROR, Constant.STATUS_ERROR, Constant.ERROR_MSG);
                    authorList.setValue(myResponse);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Data myResponse = new Data(Constant.CODE_ERROR, Constant.STATUS_ERROR, Constant.ERROR_MSG);
                authorList.setValue(myResponse);
            }
        });
    }

    private void getData() {
        if(executor == null){
            executor = Executors.newScheduledThreadPool(1);
        }
        executor.execute(() -> {
            List<AuthorWithBook> authors = appDatabase.getAuthorDao().getAllAuthor();
            ArrayList<Author> mAuthorList = new ArrayList<>();
            Data data1 = new Data(Constant.CODE_SUCCESS, Constant.STATUS_SUCCESS, "");
            for (AuthorWithBook author : authors) {
                List<Book> books = author.books;
                author.author.setBooks(books);
                mAuthorList.add(author.author);
            }
            data1.authors = mAuthorList;
            if (data1.authors.size() > 0) {
                authorList.postValue(data1);
            } else {
                loadAuthors();
            }
        });
    }

    public void setCurrentSelected(int currentSelected) {
        this.currentSelected.setValue(currentSelected);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (executor != null)
            executor.shutdown();
        executor = null;
    }
}
