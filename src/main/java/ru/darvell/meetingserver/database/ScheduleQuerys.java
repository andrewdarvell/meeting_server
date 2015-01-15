package ru.darvell.meetingserver.database;

import ru.darvell.dbwork.Worker;
import ru.darvell.meetingserver.entitys.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Запросы к базе
 */
public class ScheduleQuerys {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	//TODO возврат id
	public static int addSchedule(Schedule schedule){
		try{
			String query = "INSERT INTO `schedule`(`uid`, `date1`, `date2`, `status_id`, `status_mess`)\n" +
					"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
			ps.setInt(1, schedule.getUid());
			ps.setString(2, schedule.getDate1());
			ps.setString(3, schedule.getDate2());
			ps.setInt(4, schedule.getId());
			ps.setString(5, schedule.getStatusMess());
			ps.executeUpdate();
			Worker.commit(DB.mySqlLocal);
			ps.close();
	 		return DB.getLastInsertID();
		}catch (Exception e){
			return -8;
		}
	}

	public static int delSchedule(Schedule schedule){
		try{
			String query = "DELETE `schedule`\n" +
							"WHERE `id` = ?";
			PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
			ps.setInt(1, schedule.getId());
			ps.executeUpdate();
			Worker.commit(DB.mySqlLocal);
			ps.close();
			return 0;
		}catch (Exception e){
			return -1;
		}
	}

	public static int updateSchedule(Schedule schedule){
		try{
			String query = "UPDATE `schedule`\n" +
					"SET `status_id` = ?, `status_mess` = ?\n" +
					"WHERE ";
			PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
			ps.setInt(1, schedule.getId());
			ps.setString(2, schedule.getStatusMess());
			ps.executeUpdate();
			Worker.commit(DB.mySqlLocal);
			ps.close();
			return 0;
		}catch (Exception e){
			return -8;
		}
	}

	public static Schedule getScheduleById(int uid, int sid){
		try{
			String query = "SELECT `id`, `uid`,`date1`, `date2`, `status_id`, `status_mess`\n" +
							"FROM `schedule`\n" +
							"WHERE `uid` = ? AND `id` = ?";
			PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
			ps.setInt(1, uid);
			ps.setInt(2, sid);
			ResultSet rs = ps.executeQuery();
			Schedule schedule = null;
			while (rs.next()){
				schedule = new Schedule(rs.getInt("id"),
						rs.getInt("uid"),
						rs.getString("date1"),
						rs.getString("date2"),
						rs.getInt("status_id"),
						rs.getString("status_mess"),
						true);
			}
			rs.close();
			ps.close();
			return schedule;
		}catch (Exception e){
			return null;
		}
	}

	public static ArrayList<Schedule> getUserSchedules(int uid){
		try{
			String query = "SELECT `id`, `uid`,`date1`, `date2`, `status_id`, `status_mess`\n" +
					"FROM `schedule`\n" +
					"WHERE `uid` = ?";
			PreparedStatement ps = Worker.getDbStatement(DB.mySqlLocal, query);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			ArrayList<Schedule> schedules = new ArrayList<>();
			while (rs.next()){
				Schedule schedule = new Schedule(rs.getInt("id"),
									rs.getInt("uid"),
									rs.getString("date1"),
									rs.getString("date2"),
									rs.getInt("status_id"),
									rs.getString("status_mess"),
									true);
				schedules.add(schedule);
			}
			rs.close();
			ps.close();
			if (schedules.size()>0){
				return schedules;
			}else {
				return null;
			}
		}catch (Exception e){
			return null;
		}
	}

}
