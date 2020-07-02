package by.jwd.lemesheuski.hostel.bean;

import java.io.Serializable;

public class NumberOfBeds implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int beds;

    public NumberOfBeds() {
    }

    public NumberOfBeds(int id, int beds) {
        this.id = id;
        this.beds = beds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }
}
