package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.ApartmentType;
import by.jwd.lemesheuski.hostel.bean.NumberOfBeds;
import by.jwd.lemesheuski.hostel.bean.NumberOfRooms;

import java.util.List;

public interface ApartmentDao {

    List<ApartmentType> findAllApartmentTypes() throws DAOException;
    List<NumberOfBeds> findAllNumberOfBeds() throws DAOException;
    List<NumberOfRooms> findAllNumberOfRooms() throws DAOException;
    List<Apartment> findAllApartments() throws DAOException;
    boolean saveApartment(Apartment apartment, ApartmentType apartmentType, NumberOfBeds numberOfBeds,
                          NumberOfRooms numberOfRooms) throws DAOException;
}
