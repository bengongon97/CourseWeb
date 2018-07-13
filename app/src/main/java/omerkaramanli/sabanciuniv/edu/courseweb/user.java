package omerkaramanli.sabanciuniv.edu.courseweb;

/**
 * Created by okuyucu on 5.05.2018.
 */

public class user {
    String username;
    String password;
    String usertype;
    String name;
    String lastname;

    public user(String uname, String pass, String type, String nam, String lastnam) {
        username=uname;
        password= pass;
        usertype = type;
        name = nam;
        lastname = lastnam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
