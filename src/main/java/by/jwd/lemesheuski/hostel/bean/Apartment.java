package by.jwd.lemesheuski.hostel.bean;

import java.io.Serializable;

public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int floor;
    private int roomNumber;
    private String apartmentType;
    private boolean balcony;
    private int numberOfRooms;
    private int numberOfBeds;
    private double price;
    private String status;

    public Apartment() {
    }

    public Apartment(int id, int floor, int roomNumber, String apartmentType, boolean balcony, int numberOfRooms,
                     int numberOfBeds, double price) {
        this.id = id;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.apartmentType = apartmentType;
        this.balcony = balcony;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
    }

    public Apartment(int id, int floor, int roomNumber, String apartmentType, boolean balcony, int numberOfRooms,
                     int numberOfBeds, double price, String status) {
        this.id = id;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.apartmentType = apartmentType;
        this.balcony = balcony;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (id != apartment.id) return false;
        if (floor != apartment.floor) return false;
        if (roomNumber != apartment.roomNumber) return false;
        if (balcony != apartment.balcony) return false;
        if (numberOfRooms != apartment.numberOfRooms) return false;
        if (numberOfBeds != apartment.numberOfBeds) return false;
        if (Double.compare(apartment.price, price) != 0) return false;
        if (apartmentType != null ? !apartmentType.equals(apartment.apartmentType) : apartment.apartmentType != null)
            return false;
        return status != null ? status.equals(apartment.status) : apartment.status == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + floor;
        result = 31 * result + roomNumber;
        result = 31 * result + (apartmentType != null ? apartmentType.hashCode() : 0);
        result = 31 * result + (balcony ? 1 : 0);
        result = 31 * result + numberOfRooms;
        result = 31 * result + numberOfBeds;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", floor=" + floor +
                ", roomNumber=" + roomNumber +
                ", apartmentType='" + apartmentType + '\'' +
                ", balcony=" + balcony +
                ", numberOfRooms=" + numberOfRooms +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
