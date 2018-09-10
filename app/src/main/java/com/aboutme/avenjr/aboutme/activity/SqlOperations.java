package com.aboutme.avenjr.aboutme.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.sqlOperations.AddUser;
import com.aboutme.avenjr.aboutme.fragment.sqlOperations.RemoveUser;
import com.aboutme.avenjr.aboutme.fragment.sqlOperations.UpdateUser;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.AppDatabase;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.DatabaseInitializer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqlOperations extends AppCompatActivity {

    @BindView(R.id.table_row_count)
    TextView rowCount;

    @BindView(R.id.btnAdd)
    Button btnAdd;

    @BindView(R.id.update_user)
    Button btnUpdate;

    @BindView(R.id.remove_user)
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_operations);
        ButterKnife.bind(this);
        setDatabaseCount();
    }

    @OnClick(R.id.btnAdd)
    public void addUser(){
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.base_fragment);
        fragment = new AddUser();
        manager.beginTransaction()
                .add(R.id.base_fragment,fragment)
                .setCustomAnimations(R.anim.fui_slide_in_right,R.anim.fui_slide_out_left)
                .commit();
    }

    @OnClick(R.id.remove_user)
    public void removeUser(){
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.base_fragment);
        fragment = new RemoveUser();
        manager.beginTransaction()
                .add(R.id.base_fragment,fragment)
                .setCustomAnimations(R.anim.fui_slide_in_right,R.anim.fui_slide_out_left)
                .commit();
    }

    @OnClick(R.id.update_user)
    public void updateUser(){
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.base_fragment);
        fragment = new UpdateUser();
        manager.beginTransaction()
                .add(R.id.base_fragment,fragment)
                .setCustomAnimations(R.anim.fui_slide_in_right,R.anim.fui_slide_out_left)
                .commit();
    }

    @SuppressLint("StaticFieldLeak")
    private void setDatabaseCount() {
        new AsyncTask<Void, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return DatabaseInitializer.countUser(AppDatabase.getAppDatabase(SqlOperations.this));
            }

            @Override
            protected void onPostExecute(Integer integer) {
                rowCount.setText(integer.toString());
            }
        }.execute();
    }
}
