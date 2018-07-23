package com.androidkt.archpaging;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by brijesh on 18/9/17.
 */

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserItemViewHolder> {


    protected UserAdapter() {
        super(User.DIFF_CALLBACK);
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_user_list, parent, false);
        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserItemViewHolder holder, int position) {
        User user= getItem(position);
        if(user!=null) {
            holder.bindTo(user);
        }
    }

    static class UserItemViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userId;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            userName = itemView.findViewById(R.id.userName);
        }

        public void bindTo(User user) {
            userName.setText(user.firstName);
            userId.setText(String.valueOf(user.userId));
        }
    }
}
