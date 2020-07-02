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
                '}';
    }
}
