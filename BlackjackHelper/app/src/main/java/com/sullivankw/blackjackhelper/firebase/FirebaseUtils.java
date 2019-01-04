package com.sullivankw.blackjackhelper.firebase;

import com.sullivankw.blackjackhelper.model.User;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUtils {

    public static List<User> getLeaderBoard() {
        User u = new User("johnny boy", 12, "12-22-07");
        User u2 = new User("pete sampras", 9, "12-12-07");
        User u3 = new User("ronster223hgfghfghfgh367", 4, "02-20-07"); //this is about the mac chars
        List<User> users = new ArrayList<>();
        users.add(u);
        users.add(u2);
        users.add(u3);
        return users;
    }

    public static int getLowestLeaderBoardScore() {

        return 1;
    }
}
