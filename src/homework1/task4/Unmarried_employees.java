package homework1.task4;

import java.sql.Date;

public class Unmarried_employees {
    int id;
    String name;
    Date Date_Birth;
    String phone;
    String marital_status;

    public Unmarried_employees(int id, String name, Date date_Birth, String phone, String marital_status) {
        this.id = id;
        this.name = name;
        Date_Birth = date_Birth;
        this.phone = phone;
        this.marital_status = marital_status;
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

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
}
