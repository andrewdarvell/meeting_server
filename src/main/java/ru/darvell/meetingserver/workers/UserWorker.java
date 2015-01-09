package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.entitys.User;
import ru.darvell.meetingserver.utils.Response;

import java.util.Map;

/**
 * Created by darvell on 09.01.15.
 */
public class UserWorker implements Worker{
    User user;
    DB db;


    public UserWorker(){
        db = new DB();
    }



    public int storeUser(){
        try{
            return db.addUser(user);
        }catch (Exception e){
            return -22;
        }
    }

    public int createUser(Map<String, String> params){
        try {
            this.user = new User(params.get("login"), params.get("pass"), params.get("email"));
        }catch (Exception e){
            return -21;
        }
        return 0;
    }

    @Override
    public Response doAction(Map<String, String> params){
        String action = params.get("action");

        int res;

        if (action.equals("addUser")){
            db.connect();
            res = createUser(params);
            if (res != 0){return new Response(-10);}
            res = storeUser();
            if (res != 0){return new Response(-11);}
            return new Response(0);
        }
        db.disconnect();
        return new Response(-100);
    }




}
