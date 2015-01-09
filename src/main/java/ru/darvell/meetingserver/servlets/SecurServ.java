package ru.darvell.meetingserver.servlets;

import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.workers.SecurWorker;
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
 * Created by darvell on 10.01.15.
 */
public class SecurServ extends HttpServlet {


    /**
     * Actions:
     *  getKey - авторизация
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * Errors
     * -9 - Request Error
     * -11 - API_key error
     * -12 - Login Pass error
     * -13 - error create session
     * -100 - hz
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("getKey")){
            Response response = doGetKey(req);
            printWriter.println(response.toString());
        }
    }

    Response doGetKey(HttpServletRequest req){
        try {
            SecurWorker securWorker = new SecurWorker();
            Map<String, String> map = new HashMap<String, String>();
            map.put("login", req.getParameter("login"));
            map.put("pass", req.getParameter("pass"));
            map.put("api_key", req.getParameter("api_key"));
            map.put("action", "getKey");
            return securWorker.doAction(map);
        }catch (Exception e){
            return new Response(-9);
        }
    }
}
