package ru.darvell.meetingserver.database;

import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.User;
import ru.darvell.meetingserver.utils.MD5;

import java.sql.PreparedStatement;


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
            return -31;
        }
    }

    public void disconnect(){
        if (connected){
            Worker.closeConnection(mySqlLocal);
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
            System.out.println(e);
            return -32;
        }
    }
}
