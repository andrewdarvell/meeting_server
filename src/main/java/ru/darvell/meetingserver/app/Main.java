package ru.darvell.meetingserver.app;


import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.workers.SecurWorker;
import ru.darvell.meetingserver.workers.UserWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args){
//        try {
//            UserWorker userWorker = new UserWorker();
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("login", "22");
//            map.put("pass", "222");
//            map.put("email", "2222");
//            map.put("action", "addUser");
//            System.out.println(userWorker.doAction(map));
//        }catch (Exception e){
//            System.out.println("11111");
//        }

//        try {
//            SecurWorker securWorker = new SecurWorker();
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("login", "newuser");
//            map.put("pass", "11");
//            map.put("api_key", "bcbe3365e6ac95ea2c0343a2395834dd");
//            map.put("action", "getKey");
//            System.out.println(securWorker.doAction(map));
//        }catch (Exception e){
//            System.out.println(-9);
//        }

//        try{
//            UserWorker userWorker = new UserWorker();
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("session_key", "2893dc02b886df1bd046b5741692194e");
//            map.put("status", "3");
//            map.put("status_mess", "my status");
//            map.put("action","setStatus");
//            System.out.println(userWorker.doAction(map));
//        }catch (Exception e){
//            System.out.println(-9);
//        }

        try{
            UserWorker userWorker = new UserWorker();
            Map<String, String> map = new HashMap<String, String>();
            map.put("session_key", "2893dc02b886df1bd046b5741692194e");
            map.put("action","getStatus");
            System.out.println(userWorker.doAction(map));
        }catch (Exception e){
            System.out.println(-9);
        }

    }
}
