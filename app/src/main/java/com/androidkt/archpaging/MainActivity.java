package com.androidkt.archpaging;

import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.androidkt.archpaging.db.AppDatabase;
import com.androidkt.archpaging.db.DatabaseCreator;
import com.androidkt.archpaging.db.dao.UserDao;
import com.androidkt.archpaging.model.UserViewModel;

public class MainActivity extends AppCompatActivity {

    AppDatabase appDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = Room.databaseBuilder(MainActivity.this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        userDao = appDatabase.userDao();

        RecyclerView recyclerView = findViewById(R.id.userList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        UserViewModel viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.init(userDao);
        final UserAdapter userUserAdapter = new UserAdapter();

        viewModel.userList.observe(this, pagedList -> {
            userUserAdapter.submitList(pagedList);
        });

        recyclerView.setAdapter(userUserAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_insert:
                insertUser(appDatabase);
                break;
            case R.id.action_update:

                User user = new User();
                user.userId = 5;
                user.firstName = "William Liam";
                user.address = "I don't know";

                updateUser(user);
                break;
            case R.id.action_delete:
                deleteUser(6);
                break;
        }
        return true;
    }

    private void deleteUser(int userId) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userDao().deleteUser(userId);
                return null;
            }
        }.execute();
    }

    private void updateUser(User user) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userDao().updateUser(user);
                return null;
            }
        }.execute();

    }

    public void insertUser(final AppDatabase appDatabase) {
        final DatabaseCreator databaseCreator = new DatabaseCreator();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userDao().insertAll(databaseCreator.getRandomUserList());
                return null;
            }
        }.execute();
    }


}
