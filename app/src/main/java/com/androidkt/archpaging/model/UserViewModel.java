package com.androidkt.archpaging.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.androidkt.archpaging.User;
import com.androidkt.archpaging.db.dao.UserDao;

/**
 * Created by brijesh on 18/9/17.
 */

public class UserViewModel extends ViewModel {

    public LiveData<PagedList<User>> userList;

    public UserViewModel() {

    }

    public void init(UserDao userDao) {
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                        .setPrefetchDistance(10)
                        .setPageSize(20).build();

        userList = (new LivePagedListBuilder(userDao.usersByFirstName(), pagedListConfig))
                .build();

    }
}
