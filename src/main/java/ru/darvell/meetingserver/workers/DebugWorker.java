package ru.darvell.meetingserver.workers;


import ru.darvell.meetingserver.database.DB;
import ru.darvell.meetingserver.database.DebugQuerys;
import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;

import java.util.Map;

/**
 * For debug servlet
 */
public class DebugWorker implements Worker {

	DB db;

	public DebugWorker(){
		db = new DB();
	}

	@Override
	public Response doAction(Map<String, String> params) {
		String action = params.get("action");
		Response response = new ResponseParams(-100);
		if (action.equals("getAppInfo")){
			response = getAppInfo(params);
		}
		db.disconnect();
		return response;
	}

	Response getAppInfo(Map<String, String> params){
		int res;
		db.connect();
		res = DebugQuerys.getUserCount();
		if(res >= 0){
			return new ResponseParams(0, String.valueOf(res));
		}else{
			return new ResponseParams(-2);
		}


	}
}
