package ru.darvell.meetingserver.utils;

import ru.darvell.meetingserver.entitys.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * Ответ состоящий из набора параметров
 */
public class ResponseParams implements Response{
    String stringData = null;
    int code = -1;
    Map<String, String> paramSet = null;




    public ResponseParams(int code, String stringData) {
        this.stringData = stringData;
        this.code = code;
    }

    public ResponseParams(int code) {
        this.code = code;
    }

    public ResponseParams(){

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
