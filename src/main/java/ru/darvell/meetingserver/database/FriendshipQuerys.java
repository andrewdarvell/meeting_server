package ru.darvell.meetingserver.database;


import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by darvell on 14.01.15.
 */
public class FriendshipQuerys {

    public static int addRequest(int uid1, int uid2){
        try{
            String query = "INSERT INTO `friendship_request`(`uid1`, `uid2`)\n" +
                    "VALUES (?, ?)";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid1);
            ps.setInt(2, uid2);
            ps.executeUpdate();
            Worker.commit(DB.mySqlLocal);
            ps.close();
            return 0;

        }catch (Exception e){
            return -1;
        }
    }

    public static int checkRequestExist(int uid1, int uid2){
        try{
            String query = "SELECT `id`\n" +
                    "FROM `friendship_request`\n" +
                    "WHERE `uid1` = ?\n" +
                    "AND `uid2` = ?";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid1);
            ps.setInt(2, uid2);
            ResultSet rs = ps.executeQuery();
            int res = 0;
            while (rs.next()){
                res++;
            }
            rs.close();
            ps.close();
            return res;
        }catch (Exception e){
            return -8;
        }
    }

    public static void delRequest(int uid1, int uid2){
        try{
            String query = "DELETE FROM `friendship_request`\n" +
                    "WHERE `uid1` = ? AND `uid2` = ?";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid1);
            ps.setInt(2, uid2);
            ps.executeUpdate();
            Worker.commit(DB.mySqlLocal);
            ps.close();

        }catch (Exception e){

        }
    }

    public static int commitRequest(int uid1, int uid2){
        try{
            String query = "INSERT INTO `friendship`(`uid`, `friend_uid`)\n" +
                    "VALUES (?, ?)";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid1);
            ps.setInt(2, uid2);
            ps.executeUpdate();
            ps.setInt(2, uid1);
            ps.setInt(1, uid2);
            ps.executeUpdate();
            Worker.commit(DB.mySqlLocal);
            ps.close();
            return 0;
        }catch (Exception e){
            return -8;
        }
    }

    public static ArrayList<User> getFriendRequests(int uid){
        try{
            String query = "SELECT `uid1`\n" +
                    "FROM `friendship_request`\n" +
                    "WHERE `uid2` = ?";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            ArrayList<User> result = new ArrayList<>();
            while (rs.next()){
                User user = UserQuerys.getUserByID(rs.getInt("uid1"));
                result.add(user);
            }
            rs.close();
            ps.close();
            if (result.size() > 0){
                return  result;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static int checkIfFriend(int uid, int fuid){
        try{
            String query = "SELECT id\n" +
                    "FROM `friendship`\n" +
                    "WHERE `uid` = ?\n" +
                    "AND `friend_uid` = ?";
            PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
            ps.setInt(1, uid);
            ps.setInt(2, fuid);
            ResultSet rs = ps.executeQuery();
            int res = 0;
            while (rs.next()){
                res++;
            }
            rs.close();
            ps.close();
            if (res == 0){
                return 0;
            }else {
                return -1;
            }
        }catch (Exception e){
            return -8;
        }
    }


}
