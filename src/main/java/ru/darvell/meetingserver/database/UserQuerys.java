package ru.darvell.meetingserver.database;

import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by darvell on 14.01.15.
 */
public class UserQuerys {

    public static User getUserByID(int uid){
        try{
            String query = "SELECT `id`, `login`, `pass`, `email`, `status_id`, `status_mess`\n" +
                    "FROM `users`\n" +
                    "WHERE `id` = ?";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getInt("status_id"),
                        rs.getString("status_mess"),
                        true);
            }
            rs.close();
            ps.close();
            return user;
        }catch (Exception e){
            return null;
        }
    }

    public static ArrayList<User> findUsers(String login){
        try{String query = "SELECT `id`, `login`, `pass`, `email`, `status_id`, `status_mess`\n" +
                "FROM `users`\n" +
                "WHERE `login` LIKE ?";

            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setString(1, "%" + login + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getInt("status_id"),
                        rs.getString("status_mess"),
                        true
                );
                users.add(user);
            }
            rs.close();
            ps.close();
            if (users.size() > 0){
                return users;
            }else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public static int checkUserLogin(User user){
        try{
            String query = "SELECT `login`" +
                    "FROM `users`\n" +
                    "WHERE `login` = ?";

            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()){
                count ++;
            }
            rs.close();
            ps.close();
            return count;

        }catch (Exception e){
            e.printStackTrace();
            return -8;
        }
    }

    public static int checkUserEmail(User user){
        try{
            String query = "SELECT `email`" +
                    "FROM `users`\n" +
                    "WHERE `email` = ?";

            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()){
                count ++;
            }
            rs.close();
            ps.close();
            return count;

        }catch (Exception e){
            e.printStackTrace();
            return -8;
        }
    }
}
