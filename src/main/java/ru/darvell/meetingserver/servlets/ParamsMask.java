package ru.darvell.meetingserver.servlets;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by darvell on 21.01.15.
 */
public class ParamsMask {

	public static Map<String, String> checkRegisterParams(HttpServletRequest req){
		String s[] = {"login", "pass", "action", "email"};
		Map<String, String> map = new HashMap<>();
		try {
			for (int i = 0; i<s.length; i++) {
				String param = req.getParameter(s[i]);
				if ((param == null) || (param.equals(""))) {
					return null;
				}
				map.put(s[i], param);
			}
			map.put("action", "addUser");
			return map;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}

}
