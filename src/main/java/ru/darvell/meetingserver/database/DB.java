package ru.darvell.meetingserver.database;

import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.User;
import ru.darvell.meetingserver.utils.MD5;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Работа с БД
 */
public class DB {
    public static final String mySqlLocal = "meeting";
    static boolean connected = false;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DB(){
        ClassLoader classLoader = getClass().getClassLoader();
        String confFile = classLoader.getResource("meeting_conf.csv").getFile();
        Worker.initConfig(confFile);
        System.out.println(confFile);
    }

    public int connect(){
        if (Worker.dbConnect(mySqlLocal)) {
            connected = true;
            return 0;
        }else {
            return -8;
        }
    }

    public void disconnect(){
        if (connected){
            Worker.closeConnection(mySqlLocal);
            connected = false;
        }
    }

    public int addUser(User user){
        try {
            String query = "INSERT INTO `users` (`login`, `pass`, `email`)\n" +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Worker.getDbStatement(mySqlLocal, query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, MD5.getMd5(user.getPass()));
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            Worker.commit(mySqlLocal);
            preparedStatement.close();
            return 0;
        }catch (Exception e){
            return -8;
        }
    }

    public int checkApiKey(String key){
        try{
            String query = "SELECT `key`\n" +
                    "FROM `api_keys`\n" +
                    "WHERE `key` = ?";
            PreparedStatement preparedStatement = Worker.getDbStatement(mySqlLocal, query);
            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            int count = 0;
            while (rs.next()){
                count ++;
            }
            rs.close();
            preparedStatement.close();
            if (count == 1) {
                return 0;
            }
            return -1;
        }catch (Exception e){
            return -8;
        }
    }

    public int checkLoginPass(String login, String pass){
        try{
            String query = "SELECT id\n" +
                            "FROM users\n" +
                            "WHERE login =? \n" +
                                "\tAND pass =? ";
            PreparedStatement preparedStatement = Worker.getDbStatement(mySqlLocal, query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, MD5.getMd5(pass));
            ResultSet rs = preparedStatement.executeQuery();
            int count = 0;
            int id = -1;
            while (rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
            preparedStatement.close();

            return id;
        }catch (Exception e){
            return -8;
        }
    }

    public int storeSession(String key, int userId){
        try{
            if (killUserSessions(userId) == 0) {

                String query = "INSERT INTO `sessions`(`key`, `start_date`, `stop_date`, `uid`, `active`)\n" +
                        "VALUES (?, ?, ?, ?, 0)";
                PreparedStatement ps = Worker.getDbStatement(mySqlLocal, query);
                ps.setString(1, key);

                java.util.Date date1 = new java.util.Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date1);
                cal.add(Calendar.HOUR, 3);
                ps.setString(2, dateFormat.format(date1));
                ps.setString(3, dateFormat.format(cal.getTime()));

                ps.setInt(4, userId);
                ps.executeUpdate();
                Worker.commit(mySqlLocal);
                ps.close();
                return 0;
            }else {
                return -8;
            }
        }catch (Exception e){
            return -8;
        }
    }

    public int killUserSessions(int uid){
        try {
            String query = "UPDATE `sessions`\n" +
                    "SET `active` = -1\n" +
                    "WHERE `uid` = ?";
            PreparedStatement ps = Worker.getDbStatement(mySqlLocal, query);
            ps.setInt(1, uid);
            ps.executeUpdate();
            Worker.commit(mySqlLocal);
            ps.close();
            return 0;
        }catch (Exception e){
            return -8;
        }
    }

    public int checkSessionKey(String key){
        try {
            String query = "SELECT `uid`\n" +
                    "FROM `sessions`\n" +
                    "WHERE `key` = ?\n" +
                    "AND `active` = 0";
            PreparedStatement ps = Worker.getDbStatement(mySqlLocal, query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            int uid = -1;
            while (rs.next()){
                uid = rs.getInt("uid");
            }
            rs.close();
            ps.close();
            return uid;
        }catch (Exception e){
            return -8;
        }
    }
}
