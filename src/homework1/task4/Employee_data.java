package homework1.task4;

public class Employee_data {
    int id;
    String name;
    String phone;
    String place_residence;

    public Employee_data(int id, String name, String phone, String place_residence) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.place_residence = place_residence;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace_residence() {
        return place_residence;
    }

    public void setPlace_residence(String place_residence) {
        this.place_residence = place_residence;
    }

  }
