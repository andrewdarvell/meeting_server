package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.database.ScheduleQuerys;
import ru.darvell.meetingserver.entitys.Schedule;
import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;
import ru.darvell.meetingserver.utils.ResponseSchedules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * Обработчик расписаний
 */
public class ScheduleWorker implements Worker{
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DB db;

	public ScheduleWorker(){
		db = new DB();
	}


	Schedule createSchedule (int uid, Map<String, String> params){
		try{
			Schedule schedule = new Schedule(uid
					,params.get("date1")
					,params.get("date2")
					,Integer.parseInt(params.get("status_id"))
					,params.get("status_mess")
					,false
					);
			return schedule;
		}catch (Exception e){
			return null;
		}
	}

	boolean refresh(Schedule schedule, Map<String, String> params){
		try{
			schedule.setDate1(params.get("date1"));
			schedule.setDate2(params.get("date2"));
			schedule.setStatusId(Integer.parseInt(params.get("status_id")));
			schedule.setstatusMess(params.get("status_mess"));
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	public Response doAction(Map<String, String> params) {
		String action = params.get("action");
		Response response = new ResponseParams(-100);
		db.connect();
		if (action.equals("addSchedule")){
			response = addSchedule(params);
		}else if (action.equals("delSchedule")){
			response = delSchedule(params);
		}else if (action.equals("updateSchedule")){
			response = updateSchedule(params);
		}else if (action.equals("getUserSchedules")){
			response = getUserSchedules(params);
		}

		db.disconnect();
		return response;
	}

	Response addSchedule(Map<String, String> params){
		int res;
		int uid = db.checkSessionKey(params.get("session_key"));
		if (uid < 0){return new ResponseParams(-99);}
		//Создаем расписание из параметров
		Schedule schedule = createSchedule(uid, params);
		if (schedule == null){return new ResponseParams(-9);}
		//Сохраняем расписание в БД
		res = ScheduleQuerys.addSchedule(schedule);
		//Проверка сохранилось ли
		if (res <= 0){return new ResponseParams(-11);}
		return new ResponseParams(0, String.valueOf(res));
	}

	Response delSchedule(Map<String, String> params){
		int res;
		int uid = db.checkSessionKey(params.get("session_key"));
		if (uid < 0){return new ResponseParams(-99);}
		//Пытаемся найти в БД расписание по его sid
		Schedule schedule = ScheduleQuerys.getScheduleById(uid, Integer.parseInt(params.get("sid")));
		if (schedule == null){return new ResponseParams(-10);}
		//Удаляем расписание
		res = ScheduleQuerys.delSchedule(schedule);
		if (res != 0){return new ResponseParams(-12);}
		return new ResponseParams(0);
	}

	Response updateSchedule(Map<String, String> params){
		int uid = db.checkSessionKey(params.get("session_key"));
		if (uid < 0){return new ResponseParams(-99);}
		//Пытаемся найти в БД расписание по его sid
		Schedule schedule = ScheduleQuerys.getScheduleById(uid, Integer.parseInt(params.get("sid")));
		if (schedule == null){return new ResponseParams(-10);}
		//Обновляем распписание переданной инфой
		if (!refresh(schedule, params)){return new ResponseParams(-9);}
		//Обновляем расписание в БД
		if (ScheduleQuerys.updateSchedule(schedule) != 0){return new ResponseParams(-13);}
		else {return new ResponseParams(0);}
	}

	Response getUserSchedules(Map<String, String> params){
		int uid = db.checkSessionKey(params.get("session_key"));
		if (uid < 0){return new ResponseParams(-99);}
		//Получаем список расписаний
		ResponseSchedules resp = new ResponseSchedules(-14);
		ArrayList<Schedule> schedules = ScheduleQuerys.getUserSchedules(uid);
		if (schedules == null){	return resp;}
		resp.setCode(0);
		resp.setScheduleSet(schedules);
		return resp;
	}


}
