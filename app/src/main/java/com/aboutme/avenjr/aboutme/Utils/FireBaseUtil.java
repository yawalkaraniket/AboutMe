package com.aboutme.avenjr.aboutme.Utils;

import com.aboutme.avenjr.aboutme.activity.UserInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by AvenjR on 19/4/18.
 */

public class FireBaseUtil {

    public static DatabaseReference  getFireBaseReference(String info){
        return  FirebaseDatabase.getInstance().getReference(info);
    }
    public static void saveInformation(UserInformation userInformation, DatabaseReference mDatabaseReference){
        String UID;
        UserInformation mUserInformation = new UserInformation();
        UID = mDatabaseReference.push().getKey() ;
        mUserInformation.setDatabaseKey(UID);
        mDatabaseReference.child(UID).setValue(userInformation);
    }
    public static void saveInformationToUser(ArrayList list, DatabaseReference databaseReference){
        UserInformation mUserInformation = new UserInformation();
        databaseReference.child(mUserInformation.getDatabaseKey()).setValue(list.toString());
    }
}
