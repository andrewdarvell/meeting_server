package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;

import java.util.Map;

/**
 * Обработчик расписаний
 */
public class ScheduleWorker implements Worker{
	DB db;

	ScheduleWorker(){
		db = new DB();
	}


	@Override
	public Response doAction(Map<String, String> params) {
		String action = params.get("action");
		Response response = new ResponseParams(-100);
		db.connect();
		if (action.equals("addFriendRequest")){
//			response = addRequest(params);
		}
		db.disconnect();
		return response;
	}
}
