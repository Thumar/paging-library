package com.androidkt.archpaging.db.dao;

import androidx.paging.DataSource;
import androidx.paging.LivePagedListProvider;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.androidkt.archpaging.User;

import java.util.List;

/**
 * Created by brijesh on 18/9/17.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(User... user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateUser(User... user);

    @Query("delete from user where user_id=:id")
    int deleteUser(int id);

    @Query("SELECT * FROM User")
    public abstract DataSource.Factory<Integer,User> usersByFirstName();

}
