package com.aboutme.avenjr.aboutme.firebaseNotificaion;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }

}
