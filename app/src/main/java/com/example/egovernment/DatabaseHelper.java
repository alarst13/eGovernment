package com.example.egovernment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.egovernment.Controllers.UsersController;
import com.example.egovernment.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    Context context;
    DatabaseAccess databaseAccess;

    public DatabaseHelper(Context context) {
        databaseAccess = new DatabaseAccess(context);
    }

    public void addToDataBase(Object object) {
        if(object instanceof User) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("phone_number", ((User) object).getPhone_number());
            databaseAccess.getDb().insert("Users", null, contentValues);
            refreshList();
        }
    }
    public void refreshList(){
            List<User> users = new ArrayList<>();
            Cursor c = databaseAccess.getDb().rawQuery("SELECT * FROM Users",null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                String phoneNumber = c.getString(0);
                User user = new User(phoneNumber);
                users.add(user);
                c.moveToNext();
        }
            UsersController.addToListFromDatabase(users);
    }
}
