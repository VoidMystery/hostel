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
    Apartment findApartmentById(int id) throws DAOException;
    Apartment findApartmentByRoomNumber(int roomNumber) throws DAOException;
    boolean saveApartment(Apartment apartment, ApartmentType apartmentType, NumberOfBeds numberOfBeds,
                          NumberOfRooms numberOfRooms) throws DAOException;
    boolean saveApartmentType(String type) throws DAOException;
    boolean saveNumberOfBeds(int nob) throws DAOException;
    boolean saveNumberOfRooms(int nor) throws DAOException;
    boolean updateApartment(Apartment apartment, ApartmentType apartmentType, NumberOfBeds numberOfBeds,
                          NumberOfRooms numberOfRooms) throws DAOException;
    boolean deactivateApartment(int roomNumber) throws DAOException;
    boolean updateApartmentType(ApartmentType apartmentType) throws DAOException;
    boolean updateNumberOfRooms(NumberOfRooms numberOfRooms) throws DAOException;
    boolean updateNumberOfBeds(NumberOfBeds numberOfBeds) throws DAOException;
    ApartmentType findApartmentTypeById(int id) throws DAOException;
    NumberOfBeds findNumberOfBedsById(int id) throws DAOException;
    NumberOfRooms findNumberOfRoomsById(int id) throws DAOException;
}
