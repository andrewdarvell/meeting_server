package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.database.FriendshipQuerys;
import ru.darvell.meetingserver.database.UserQuerys;
import ru.darvell.meetingserver.entitys.User;
import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;
import ru.darvell.meetingserver.utils.ResponseUsers;

import java.util.ArrayList;
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
            return -1;
        }
    }

    public int createUser(Map<String, String> params){
        try {
            this.user = new User(params.get("login"), params.get("pass"), params.get("email"));
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public Response doAction(Map<String, String> params){
        String action = params.get("action");
        Response response = new ResponseParams(-100);

        if (action.equals("addUser")){
            response = addUser(params);
        }else if(action.equals("setStatus")){
            response = setStatus(params);
        }else if(action.equals("getStatus")){
            response = getStatus(params);
        }else if(action.equals("findUser")){
            response = findUser(params);
        }

        db.disconnect();
        return response;
    }

    public Response addUser(Map<String, String> params){
        int res;
        db.connect();
        res = createUser(params);
        if (res != 0){return new ResponseParams(-10);}
        //ПРоверяем логин на дубли
        if (UserQuerys.checkUserLogin(user) != 0){return new ResponseParams(-15);}
        //Проверяем почту на дубли
        if (UserQuerys.checkUserEmail(user) != 0){return new ResponseParams(-16);}
        //Пытаемся сохранить в базу
        res = storeUser();
        if (res != 0){return new ResponseParams(-11);}
        return new ResponseParams(0);

    }

    public Response setStatus(Map<String, String> params){
        int res;
        db.connect();
        int uid = db.checkSessionKey(params.get("session_key"));
        if (uid < 0){return new ResponseParams(-99);}
        res = db.setStatus(uid, params.get("status"), params.get("status_mess"));
        if (res != 0){return new ResponseParams(-12);}
        return new ResponseParams(0);
    }

    public Response getStatus(Map<String, String> params){
        db.connect();
        int uid = db.checkSessionKey(params.get("session_key"));
        if (uid < 0){return new ResponseParams(-99);}
        Map<String, String> resSet = db.getStatus(uid);
        ResponseParams response = new ResponseParams(-13);
        if (resSet != null){
            response.setCode(0);
            response.setParamSet(resSet);
        }
        return response;
    }

    public Response findUser(Map<String,String> params){
        db.connect();
        int uid = db.checkSessionKey(params.get("session_key"));
        if (uid < 0){return new ResponseParams(-99);}
        ArrayList<User> resSet = UserQuerys.findUsers(params.get("login"));
        ResponseUsers response = new ResponseUsers(-14);
        if (resSet != null){
            response.setCode(0);
            response.setUserSet(resSet);
        }
        return response;
    }




}
