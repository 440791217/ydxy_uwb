package org.xiaomi.entity;

public class User {
    private String name;
    private String passwd;

    // 构造方法、Getter 和 Setter 方法
    public User() {}

    public User(String user, String passwd) {
        this.name = user;
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}