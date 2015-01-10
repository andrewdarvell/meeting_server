package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.utils.MD5;
import ru.darvell.meetingserver.utils.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Response resp = new Response();
        String action = params.get("action");
        int res;
        if (action.equals("getKey")){
            db.connect();
            res = db.checkApiKey(params.get("api_key"));
            if (res != 0){return new Response(-11);}
            int uid = db.checkLoginPass(params.get("login"), params.get("pass"));
            if (uid < 0) {return new Response(-12);}
            String sessionKey = genSessionKey(params.get("login"),
                    params.get("pass"),
                    params.get("api_key"));
            res = db.storeSession(sessionKey, uid);
            if (res != 0) {return new Response(-13);}
            return new Response(0, sessionKey);
        }
        db.disconnect();
        return new Response(-100);
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
