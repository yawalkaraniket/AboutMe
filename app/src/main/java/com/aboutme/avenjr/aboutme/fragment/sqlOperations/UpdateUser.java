package com.aboutme.avenjr.aboutme.fragment.sqlOperations;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.AppDatabase;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.DatabaseInitializer;
import com.aboutme.avenjr.aboutme.sqlLiteHandler.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateUser extends Fragment {

    @BindView(R.id.input_first_name)
    TextInputLayout inputFirstName;

    @BindView(R.id.input_last_name)
    TextInputLayout inputLastName;

    @BindView(R.id.input_age)
    TextInputLayout inputAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_user, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @SuppressLint("StaticFieldLeak")
    @OnClick(R.id.btnSave)
    public void save() {
        User user = new User();
        user.setFirstName(inputFirstName.getEditText().getText().toString());
        user.setLastName(inputLastName.getEditText().getText().toString());
        user.setAge(Integer.parseInt(inputAge.getEditText().getText().toString()));
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseInitializer.saveUser(AppDatabase.getAppDatabase(getActivity().getApplicationContext()), user);
                return null;
            }
        }.execute();
    }
}
