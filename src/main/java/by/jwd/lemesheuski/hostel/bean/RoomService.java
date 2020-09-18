package by.jwd.lemesheuski.hostel.bean;

import java.io.Serializable;

public class RoomService implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String roomServiceName;
    private double price;
    private double cost;
    private int shoId;

    public RoomService() {
    }

    public RoomService(int id, String roomServiceName, double price) {
        this.id = id;
        this.roomServiceName = roomServiceName;
        this.price = price;
    }
    public RoomService(int id, String roomServiceName, double price, double cost) {
        this.id = id;
        this.roomServiceName = roomServiceName;
        this.price = price;
        this.cost = cost;
    }
    public RoomService(int id, String roomServiceName, double price, double cost, int shoId) {
        this.id = id;
        this.roomServiceName = roomServiceName;
        this.price = price;
        this.cost = cost;
        this.shoId = shoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomServiceName() {
        return roomServiceName;
    }

    public void setRoomServiceName(String roomServiceName) {
        this.roomServiceName = roomServiceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getShoId() {
        return shoId;
    }

    public void setShoId(int shoId) {
        this.shoId = shoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomService that = (RoomService) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (shoId != that.shoId) return false;
        return roomServiceName != null ? roomServiceName.equals(that.roomServiceName) : that.roomServiceName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (roomServiceName != null ? roomServiceName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + shoId;
        return result;
    }

    @Override
    public String toString() {
        return "RoomService{" +
                "id=" + id +
                ", roomServiceName='" + roomServiceName + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", shoId=" + shoId +
                '}';
    }
}
