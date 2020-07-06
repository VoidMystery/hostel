package by.jwd.lemesheuski.hostel.bean;

import java.io.Serializable;

public class NumberOfRooms implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int rooms;

    public NumberOfRooms() {
    }

    public NumberOfRooms(int id, int rooms) {
        this.id = id;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "NumberOfRooms{" +
                "id=" + id +
                ", rooms=" + rooms +
                '}';
    }
}
