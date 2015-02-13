package ru.darvell.meetingserver.database;

import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Querys for debug
 */
public class DebugQuerys {

	public static int getUserCount() {
		try {
			int result = -1;
			String query = "SELECT COUNT(`id`) AS `count`\n" +
						"FROM `users`";
			PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count");
			}
			rs.close();
			ps.close();
			return result;
		} catch (Exception e) {
			return -2;
		}
	}

}
