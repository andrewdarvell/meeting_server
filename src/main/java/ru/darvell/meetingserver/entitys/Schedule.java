package ru.darvell.meetingserver.entitys;

import java.util.Date;

/**
 * Расписание
 */
public class Schedule {
	int id;
	int uid;
	String date1;
	String date2;
	int statusId;
	String statusMess;
	boolean stored = false;

	public Schedule(int uid, String date1, String date2, int statusId, String statusMess, boolean stored) {
		this.uid = uid;
		this.date1 = date1;
		this.date2 = date2;
		this.statusId = statusId;
		this.statusMess = statusMess;
		this.stored = stored;
	}

	public Schedule(int id, int uid, String date1, String date2, int statusId, String statusMess, boolean stored) {
		this.id = id;
		this.uid = uid;
		this.date1 = date1;
		this.date2 = date2;
		this.statusId = statusId;
		this.statusMess = statusMess;
		this.stored = stored;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusMess() {
		if (statusMess == null){
			return "";
		}else {
			return statusMess;
		}
	}

	public void setstatusMess(String statusMess) {
		this.statusMess = statusMess;
	}
}
