package ru.darvell.meetingserver.utils;

import java.util.Map;

/**
 * Created by darvell on 10.01.15.
 */
public class Response {
    String stringData = null;
    int code = -1;
    Map<String, String> paramSet = null;



    public Response(int code, String stringData) {
        this.stringData = stringData;
        this.code = code;
    }

    public Response(int code) {
        this.code = code;
    }

    public Response(){

    }

    public void setParamSet(Map<String, String> paramSet){
        this.paramSet = paramSet;
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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("code:");
        sb.append(code);
        sb.append(";");
        if (stringData != null){
            sb.append("main:");
            sb.append(stringData);
            sb.append(";");
        }
        if (paramSet != null){
            for(String key : paramSet.keySet()){
                sb.append(key);
                sb.append(":");
                sb.append(paramSet.get(key));
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
