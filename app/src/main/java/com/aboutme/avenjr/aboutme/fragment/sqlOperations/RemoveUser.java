package com.aboutme.avenjr.aboutme.fragment.sqlOperations;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.AppDatabase;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.DatabaseInitializer;
import com.aboutme.avenjr.aboutme.view.DialogUtil;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemoveUser extends Fragment {

    @BindView(R.id.input_name)
    TextInputLayout userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_remove_user, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    @OnClick(R.id.btnRemove)
    public void removeUser(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseInitializer.deleteUser(AppDatabase.getAppDatabase(getActivity()),userName.getEditText().getText().toString());
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),"user has been deleted",
                        Toast.LENGTH_SHORT).show();
            }
        }.execute();
        Objects.requireNonNull(userName.getEditText()).setText("");
        getActivity().recreate();
    }
}
