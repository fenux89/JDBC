package homework1.task4;

import java.sql.Date;

public class Managers {
    int id;
    String name;
    String post;
    Date Date_Birth;
    String phone;

    public Managers(int id, String name, String post, Date date_Birth, String phone) {
        this.id = id;
        this.name = name;
        this.post = post;
        Date_Birth = date_Birth;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getDate_Birth() {
        return Date_Birth;
    }

    public void setDate_Birth(Date date_Birth) {
        Date_Birth = date_Birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
