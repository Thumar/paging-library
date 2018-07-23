package com.androidkt.archpaging.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

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
