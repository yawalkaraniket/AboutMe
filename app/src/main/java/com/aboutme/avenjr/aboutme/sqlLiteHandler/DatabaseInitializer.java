package com.aboutme.avenjr.aboutme.sqlLiteHandler;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    private static void addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
    }

    public static Integer countUser(final AppDatabase db) {
        return db.userDao().countUsers();
    }

    public static void deleteUser(final AppDatabase db, String userName) {
        db.userDao().delete(userName);
    }

    public static void saveUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
    }
}
