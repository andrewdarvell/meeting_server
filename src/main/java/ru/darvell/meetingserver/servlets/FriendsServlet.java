package ru.darvell.meetingserver.servlets;

import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;
import ru.darvell.meetingserver.workers.FriendsWorker;
import ru.darvell.meetingserver.workers.UserWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darvell on 14.01.15.
 */
public class FriendsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String action = req.getParameter("action");
        if(action.equals("addFriendRequest")){
            printWriter.println(addFriendReq(req).toString());
        }else if(action.equals("getFriendRequests")){
            printWriter.println(getFriendRequests(req).toString());
        }else if(action.equals("commitFriend")){
            printWriter.println(commitFriend(req).toString());
        }
    }

    Response addFriendReq(HttpServletRequest req){
        try{
            FriendsWorker friendsWorker = new FriendsWorker();
            Map<String, String> map = new HashMap<>();
            map.put("session_key", req.getParameter("session_key"));
            map.put("action","addFriendRequest");
            map.put("friendId", req.getParameter("friendId"));
            return friendsWorker.doAction(map);
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }

    Response getFriendRequests(HttpServletRequest req){
        try{
            FriendsWorker friendsWorker = new FriendsWorker();
            Map<String, String> map = new HashMap<>();
            map.put("session_key", req.getParameter("session_key"));
            map.put("action","getFriendRequests");
            return friendsWorker.doAction(map);
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }

    Response commitFriend(HttpServletRequest req){
        try{
            FriendsWorker friendsWorker = new FriendsWorker();
            Map<String, String> map = new HashMap<>();
            map.put("session_key", req.getParameter("session_key"));
            map.put("action","commitFriend");
            map.put("friendId", req.getParameter("friendId"));
            return friendsWorker.doAction(map);
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }


}
