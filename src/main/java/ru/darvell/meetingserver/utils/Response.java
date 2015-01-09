package ru.darvell.meetingserver.utils;

/**
 * Created by darvell on 10.01.15.
 */
public class Response {
    String stringData = null;
    int code = -1;

    public Response(int code, String stringData) {
        this.stringData = stringData;
        this.code = code;
    }

    public Response(int code) {
        this.code = code;
    }

    public Response(){

    }

    public String getStringData() {
        return stringData;
    }

    public int getCode() {
        return code;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code+";"+stringData;
    }
}
