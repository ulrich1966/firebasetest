package de.auli.firebasetest;

import java.io.Serializable;

public class Message implements Serializable {
    private String user;
    private String msg;

    public Message(){
        super();
    }

    public Message(String user){
        this();
        this.user = user;
    }

    public Message(String user, String msg){
        this();
        this.user = user;
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
}
