package ru.darvell.meetingserver.app;


import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.workers.UserWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args){
        try {
            UserWorker userWorker = new UserWorker();
            Map<String, String> map = new HashMap<String, String>();
            map.put("login", "22");
            map.put("pass", "222");
            map.put("email", "2222");
            map.put("action", "addUser");
            System.out.println(userWorker.doAction(map));
        }catch (Exception e){
            System.out.println("11111");
        }

    }
}
