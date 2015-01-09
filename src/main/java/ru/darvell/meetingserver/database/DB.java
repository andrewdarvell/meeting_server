package ru.darvell.meetingserver.database;

import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.User;
import ru.darvell.meetingserver.utils.MD5;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;


/**
 * Работа с БД
 */
public class DB {
    public static final String mySqlLocal = "meeting";
    static boolean connected = false;

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

    public int storeSession(String key){
        try{
            Calendar calendar = Calendar.getInstance();
            String query = "INSERT INTO `sessions`(`key`, `start_date`)\n" +
                        "VALUES (?, ?)";
            PreparedStatement ps = Worker.getDbStatement(mySqlLocal, query);
            ps.setString(1, key);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.executeUpdate();
            Worker.commit(mySqlLocal);
            return 0;
        }catch (Exception e){
            return -8;
        }
    }
}
