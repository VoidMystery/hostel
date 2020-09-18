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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberOfBeds that = (NumberOfBeds) o;

        if (id != that.id) return false;
        return beds == that.beds;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + beds;
        return result;
    }

    @Override
    public String toString() {
        return "NumberOfBeds{" +
                "id=" + id +
                ", beds=" + beds +
                '}';
    }
}
