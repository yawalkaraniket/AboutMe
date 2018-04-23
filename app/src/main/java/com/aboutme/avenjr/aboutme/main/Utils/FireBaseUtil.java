package com.aboutme.avenjr.aboutme.main.Utils;

import com.aboutme.avenjr.aboutme.main.activity.UserInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by AnenjR on 19/4/18.
 */

public class FireBaseUtil {

    public static DatabaseReference  getFireBaseReference(String info){
        return  FirebaseDatabase.getInstance().getReference(info);
    }
    public static void saveInformation(UserInformation userInformation, DatabaseReference mDatabaseReference){
        String UID;
        UID = mDatabaseReference.push().getKey() ;
        mDatabaseReference.child(UID).setValue(userInformation);
    }
}
