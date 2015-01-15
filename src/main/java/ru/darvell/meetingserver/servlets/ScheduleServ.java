package ru.darvell.meetingserver.servlets;

import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;
import ru.darvell.meetingserver.workers.ScheduleWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервлет с расписаниями
 */
public class ScheduleServ extends HttpServlet {
	/**
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * -9 - Request Error
	 * -10 - can't find sid
	 * -11 - can't store schedule
	 * -12 - can't del schedule
	 * -13 - can't update schedule
	 * -99 - session_key error
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		String action = req.getParameter("action");
		if(action.equals("addSchedule")) {
			printWriter.println(addSchedule(req).toString());
		}else if(action.equals("delSchedule")) {
			printWriter.println(delSchedule(req).toString());
		}else if(action.equals("updateSchedule")) {
			printWriter.println(updateSchedule(req).toString());
		}else if(action.equals("getUserSchedules")) {
			printWriter.println(getUserSchedules(req).toString());
		}
	}

	Response addSchedule(HttpServletRequest req){
		try{
			ScheduleWorker scheduleWorker = new ScheduleWorker();
			Map<String, String> map = new HashMap<>();
			map.put("session_key", req.getParameter("session_key"));
			map.put("action","addSchedule");
			map.put("date1", req.getParameter("date1"));
			map.put("date2", req.getParameter("date2"));
			map.put("status_id", req.getParameter("status_id"));
			map.put("status_mess", req.getParameter("status_mess"));
			return scheduleWorker.doAction(map);
		}catch (Exception e){
			return new ResponseParams(-9);
		}
	}

	Response delSchedule(HttpServletRequest req){
		try{
			ScheduleWorker scheduleWorker = new ScheduleWorker();
			Map<String, String> map = new HashMap<>();
			map.put("session_key", req.getParameter("session_key"));
			map.put("action","delSchedule");
			map.put("sid", req.getParameter("sid"));
			return scheduleWorker.doAction(map);
		}catch (Exception e){
			return new ResponseParams(-9);
		}
	}

	Response updateSchedule(HttpServletRequest req){
		try{
			ScheduleWorker scheduleWorker = new ScheduleWorker();
			Map<String, String> map = new HashMap<>();
			map.put("session_key", req.getParameter("session_key"));
			map.put("action","updateSchedule");
			map.put("date1", req.getParameter("date1"));
			map.put("date2", req.getParameter("date2"));
			map.put("status_id", req.getParameter("status_id"));
			map.put("status_mess", req.getParameter("status_mess"));
			return scheduleWorker.doAction(map);
		}catch (Exception e){
			return new ResponseParams(-9);
		}
	}

	Response getUserSchedules(HttpServletRequest req){
		try{
			ScheduleWorker scheduleWorker = new ScheduleWorker();
			Map<String, String> map = new HashMap<>();
			map.put("session_key", req.getParameter("session_key"));
			map.put("action","getUserSchedules");
			return scheduleWorker.doAction(map);
		}catch (Exception e){
			return new ResponseParams(-9);
		}
	}
}
