package ru.darvell.meetingserver.entitys;

/**
 * Пользователь
 */
public class User {
    int id;
    String login;
    String pass;
    String email;
    int statusId;
    String statusMess;
    boolean stored;

    public User(int id, String login, String pass, String email, int statusId, String statusMess, boolean stored) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.statusId = statusId;
        this.statusMess = statusMess;
        this.stored = stored;
    }

    public User(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public User(int id, String login, String email, int statusId, String statusMess, boolean stored) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.statusId = statusId;
        this.statusMess = statusMess;
        this.stored = stored;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusMess() {
        return statusMess;
    }

    public void setStatusMess(String statusMess) {
        this.statusMess = statusMess;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }
}
