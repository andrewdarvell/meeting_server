package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.utils.MD5;
import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;

import java.util.Map;
import java.util.Random;

/**
 * Created by darvell on 10.01.15.
 */
public class SecurWorker implements Worker{
    DB db;


    public SecurWorker(){
        db = new DB();
    }

    @Override
    public Response doAction(Map<String, String> params) {
        String action = params.get("action");
        int res;
        if (action.equals("getKey")){
            db.connect();
            res = db.checkApiKey(params.get("api_key"));
            if (res != 0){return new ResponseParams(-11);}
            int uid = db.checkLoginPass(params.get("login"), params.get("pass"));
            if (uid < 0) {return new ResponseParams(-12);}
            String sessionKey = genSessionKey(params.get("login"),
                    params.get("pass"),
                    params.get("api_key"));
            res = db.storeSession(sessionKey, uid);
            if (res != 0) {return new ResponseParams(-13);}
            return new ResponseParams(0, sessionKey);
        }
        db.disconnect();
        return new ResponseParams(-100);
    }

    public String genSessionKey(String login, String pass, String api_key){
        Random rand = new Random();
        String str = login
                +api_key
                +pass
                +String.valueOf(System.currentTimeMillis())
                +String.valueOf(rand.nextInt());
        String key = MD5.getMd5(str);
        return key;


    }
}
