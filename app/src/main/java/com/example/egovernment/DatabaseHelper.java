package com.example.egovernment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.egovernment.Controllers.UsersController;
import com.example.egovernment.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHelper {

    Context context;
    DatabaseAccess databaseAccess;

    public DatabaseHelper(Context context) {
        databaseAccess = new DatabaseAccess(context);
    }

    public void addToDataBase(Object object) {
        if(object instanceof User) {
            Random random = new Random();
            int low = 100000;
            int high = 9000000;
            int result = random.nextInt(high-low) + low;

            ContentValues contentValues = new ContentValues();
            contentValues.put("phone_number", ((User) object).getPhone_number());
            databaseAccess.getDb().insert("Users", null, contentValues);
            refreshList();

            ContentValues contentValues1 = new ContentValues();
            contentValues1.put("phone_number", ((User) object).getPhone_number());
            contentValues1.put("card_number", ((User) object).getPhone_number());
            contentValues1.put("password", ((User) object).getPhone_number());
            contentValues1.put("currency", result);
            databaseAccess.getDb().insert("BankAccount", null, contentValues1);
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
