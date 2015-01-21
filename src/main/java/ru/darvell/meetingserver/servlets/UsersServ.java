package ru.darvell.meetingserver.servlets;

import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;
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
 * Работа с пользователями
 */
public class UsersServ extends HttpServlet{
    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * -9 - Request Error
     * -10 - can't create user
     * -11 - can't store user
     * -12 - can't set status
     * -13 - can't get status
     * -14 - can't find user
     * -15 - login exist
     * -16 - email exist
     * -99 - session_key error
     * -199 - params error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("register")){
            printWriter.println(registerUser(req).toString());

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String action = req.getParameter("action");
        if(action.equals("setStatus")){
            printWriter.println(setUserStatus(req).toString());
        }else if(action.equals("getStatus")){
            printWriter.println(getStatus(req).toString());
        }else if(action.equals("findUser")){
            printWriter.println(findUser(req).toString());
        }
    }

    Response registerUser(HttpServletRequest req){
        try {
            Map<String, String> map = ParamsMask.checkRegisterParams(req);
            if (map != null){
                UserWorker userWorker = new UserWorker();
                return userWorker.doAction(map);
            }else{
                return new ResponseParams(-199);
            }
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }

    Response setUserStatus(HttpServletRequest req){
        try{
            UserWorker userWorker = new UserWorker();
            Map<String, String> map = new HashMap<>();
            map.put("session_key", req.getParameter("session_key"));
            map.put("status", req.getParameter("status"));
            map.put("status_mess",req.getParameter("status_mess"));
            map.put("action","setStatus");
            return userWorker.doAction(map);
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }

    Response getStatus(HttpServletRequest req){
        try{
            UserWorker userWorker = new UserWorker();
            Map<String, String> map = new HashMap<>();
            map.put("session_key", req.getParameter("session_key"));
            map.put("action","getStatus");
            return userWorker.doAction(map);
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }

    Response findUser(HttpServletRequest req){
        try{
            UserWorker userWorker = new UserWorker();
            Map<String, String> map = new HashMap<>();
            map.put("session_key", req.getParameter("session_key"));
            map.put("action","findUser");
            map.put("login", req.getParameter("login"));
            return userWorker.doAction(map);
        }catch (Exception e){
            return new ResponseParams(-9);
        }
    }

}
//GRANT ALL PRIVILEGES ON meeting.* TO meeting@localhost IDENTIFIED BY 'meetingvjcmrfblah' WITH GRANT OPTION;flush privileges;
