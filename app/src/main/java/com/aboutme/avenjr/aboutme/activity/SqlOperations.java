package com.aboutme.avenjr.aboutme.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
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

    @BindView(R.id.remove_button)
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_operations);
        ButterKnife.bind(this);
        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
        setDatabaseCount();
    }

    @OnClick(R.id.btnAdd)
    public void addUser(){

    }

    @OnClick(R.id.remove_button)
    public void removeUser(){

    }

    @OnClick(R.id.update_user)
    public void updateUser(){

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
