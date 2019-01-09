package com.sullivankw.blackjackhelper.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.sullivankw.blackjackhelper.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FirebaseRepo {

    private static FirebaseRepo INSTANCE;

    private static final String DB_PATH = "leaders";
    private static final String BY_HIGH_SCORE = "highScore";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private FirebaseRepo() {
    }

    //double checked locking
    public static FirebaseRepo getFirebaseRepo() {
        if(INSTANCE == null){
            synchronized (FirebaseRepo.class) {
                if(INSTANCE == null){
                    INSTANCE = new FirebaseRepo();
                }
            }
        }
        return INSTANCE;
    }

    public Query getSortedLeaderboard() {
        return getDatabaseRefNoChild().orderByChild(BY_HIGH_SCORE);
    }

    public Task<Void> deleteLeader(String id) {
        return getDatabaseRefNoChild().child(DB_PATH)
                .child(id).removeValue();
    }


    public Task<Void> addToLeaderboard(String name, int score) {
        String timeStamp = new SimpleDateFormat(DATE_FORMAT, Locale.US).format(Calendar.getInstance().getTime());
        String displayTimeStamp = timeStamp.substring(0,10);
        String id = getDatabaseKey();
        User u = new User(name, score, timeStamp, id, displayTimeStamp);
        return getDatabaseRefNoChild().child(id).setValue(u);

    }

    private DatabaseReference getDatabaseRefNoChild(){
        return FirebaseDatabase.getInstance().getReference(DB_PATH);
    }

    private String getDatabaseKey() {
        return getDatabaseRefNoChild().push().getKey();
    }

}
