package ru.darvell.meetingserver.utils;

/**
 * Created by darvell on 13.01.15.
 */
public interface Response {
	public String toString();
	public void setCode(int code);
	public int getCode();
	public void setStringData(String stringData);
	public String getStringData();



}
