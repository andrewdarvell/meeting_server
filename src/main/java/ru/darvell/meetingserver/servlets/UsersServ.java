package ru.darvell.meetingserver.servlets;

import ru.darvell.meetingserver.utils.Response;
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
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("register")){
            printWriter.println(registerUser(req));

        }
    }

    Response registerUser(HttpServletRequest req){
        try {
            UserWorker userWorker = new UserWorker();
            Map<String, String> map = new HashMap<String, String>();
            map.put("login", req.getParameter("login"));
            map.put("pass", req.getParameter("pass"));
            map.put("email", req.getParameter("email"));
            map.put("action", "addUser");
            return userWorker.doAction(map);
        }catch (Exception e){
            return new Response(-9);
        }
    }

}
//GRANT ALL PRIVILEGES ON meeting.* TO meeting@localhost IDENTIFIED BY 'meetingvjcmrfblah' WITH GRANT OPTION;flush privileges;
