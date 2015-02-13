package ru.darvell.meetingserver.servlets;

import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;
import ru.darvell.meetingserver.workers.DebugWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet for testing/debugging
 */
public class DebugServ extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		String action = req.getParameter("action");
		if(action.equals("getAppInfo")){
			printWriter.println(getAppInfo(req).toString());
		}
	}

	Response getAppInfo(HttpServletRequest req){
		try{
			DebugWorker debugWorker = new DebugWorker();
			Map<String, String> map = new HashMap<>();
			map.put("action","getAppInfo");
			return debugWorker.doAction(map);
		}catch (Exception e){
			return new ResponseParams(-9);
		}
	}
}
