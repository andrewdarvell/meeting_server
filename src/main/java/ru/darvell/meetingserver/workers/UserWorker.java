package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.entitys.User;

import java.util.Map;

/**
 * Created by darvell on 09.01.15.
 */
public class UserWorker {
    User user;
    DB db;


    public UserWorker(){
        db = new DB();
    }



    public int storeUser(){
        try{
            return db.addUser(user);
        }catch (Exception e){
            return -2;
        }
    }

    public int createUser(Map<String, String> params){
        try {
            this.user = new User(params.get("login"), params.get("pass"), params.get("email"));
        }catch (Exception e){
            return -1;
        }
        return 0;
    }

    public int doAction(Map<String, String> params){
        String action = params.get("action");

        int res;
        res = db.connect();
        if (res != 0){return res;}

        if (action.equals("addUser")){
            res = createUser(params);
            if (res != 0){return res;}
            res = storeUser();
            if (res != 0){return res;}
        }
        db.disconnect();
        return 0;
    }




}
