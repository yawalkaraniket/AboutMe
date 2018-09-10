package com.aboutme.avenjr.aboutme.fragment.sqlOperations;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddUser extends Fragment {

    @BindView(R.id.input_first_name)
    TextInputLayout inputFirstName;

    @BindView(R.id.input_last_name)
    TextInputLayout inputLastName;

    @BindView(R.id.input_age)
    TextInputLayout inputAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
