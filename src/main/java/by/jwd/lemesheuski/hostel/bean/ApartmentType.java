package by.jwd.lemesheuski.hostel.bean;

import java.io.Serializable;

public class ApartmentType implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String type;

    public ApartmentType() {
    }

    public ApartmentType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ApartmentType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}