package by.jwd.lemesheuski.hostel.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    private int id;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double price;
    private int userId;
    private String status;
    private int apartmentId;
    private int apartmentNumber;

    public Order() {
    }

    public Order(int id, LocalDate beginDate, LocalDate endDate, double price, int userId, String status, int apartmentId,
    int apartmentNumber) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.price = price;
        this.userId = userId;
        this.apartmentId = apartmentId;
        this.status = status;
        this.apartmentNumber = apartmentNumber;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (userId != order.userId) return false;
        if (apartmentId != order.apartmentId) return false;
        if (apartmentNumber != order.apartmentNumber) return false;
        if (beginDate != null ? !beginDate.equals(order.beginDate) : order.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(order.endDate) : order.endDate != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + apartmentId;
        result = 31 * result + apartmentNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", apartmentId=" + apartmentId +
                ", apartmentNumber=" + apartmentNumber +
                '}';
    }
}
