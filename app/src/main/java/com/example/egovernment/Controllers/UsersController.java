package com.example.egovernment.Controllers;
import com.example.egovernment.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersController {

    private static List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static void addToListFromDatabase(List<User> list){
        try {
            users.clear();
        } catch (Exception e){}
        users.addAll(list);
    }
    public static Boolean isPhoneNumberUnique(User currentUser){
        for(User user : users){
            if(currentUser.getPhone_number().equals(user.getPhone_number())){
                return false;
            }
        }
        return true;
    }
}
