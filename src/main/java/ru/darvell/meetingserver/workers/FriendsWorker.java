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
 * Created by darvell on 14.01.15.
 */
public class FriendsWorker implements Worker {

    DB db;

    public FriendsWorker() {
        db = new DB();
    }

    @Override
    public Response doAction(Map<String, String> params) {
        String action = params.get("action");
        Response response = new ResponseParams(-100);
        db.connect();
        if (action.equals("addFriendRequest")){
            response = addRequest(params);
        }else if (action.equals("getFriendRequests")){
            response = getRequests(params);
        }else if (action.equals("commitFriend")){
            response = commitFriend(params);
        }

        db.disconnect();
        return response;
    }

    //TODO проверка на добавление самого себя
    Response addRequest(Map<String, String> params){
        int res;
        int uid = db.checkSessionKey(params.get("session_key"));
        if (uid < 0){return new ResponseParams(-99);}
        User friend = UserQuerys.getUserByID(Integer.valueOf(params.get("friendId")));
        if (friend == null){return new ResponseParams(-10);}
        res = FriendshipQuerys.checkIfFriend(uid, friend.getId());
        if (res != 0) {
            return new ResponseParams(-11);}
        res = FriendshipQuerys.checkRequestExist(uid, friend.getId());
        if (res != 0){return new ResponseParams(-12);}
        if (FriendshipQuerys.addRequest(uid, friend.getId()) != 0){return new ResponseParams(-13);}
        return new ResponseParams(0);
    }


    Response commitFriend(Map<String, String> params){
        int res;
        int uid = db.checkSessionKey(params.get("session_key"));
        if (uid < 0){return new ResponseParams(-99);}
        User friend = UserQuerys.getUserByID(Integer.valueOf(params.get("friendId")));
        if (friend == null){return new ResponseParams(-10);}
        res = FriendshipQuerys.checkIfFriend(uid, friend.getId());
        if (res != 0) {
            return new ResponseParams(-11);}
        res = FriendshipQuerys.checkRequestExist(friend.getId(), uid);
        if (res != 1){return new ResponseParams(-12);}
        if (FriendshipQuerys.commitRequest(uid, friend.getId()) != 0){return new ResponseParams(-15);}
        FriendshipQuerys.delRequest(friend.getId(), uid);
        return new ResponseParams(0);
    }

    Response getRequests(Map<String, String> params){
        int uid = db.checkSessionKey(params.get("session_key"));
        if (uid < 0){return new ResponseParams(-99);}
        ArrayList<User> resSet = FriendshipQuerys.getFriendRequests(uid);
        ResponseUsers response = new ResponseUsers(-14);
        if (resSet != null){
            response.setCode(0);
            response.setUserSet(resSet);
        }
        return response;
    }




}
