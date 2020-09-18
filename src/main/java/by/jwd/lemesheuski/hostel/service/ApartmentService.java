package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.ApartmentType;
import by.jwd.lemesheuski.hostel.bean.NumberOfBeds;
import by.jwd.lemesheuski.hostel.bean.NumberOfRooms;

import java.util.List;

public interface ApartmentService {
    List<ApartmentType> getApartmentTypeList() throws ServiceException;

    List<NumberOfBeds> getNumberOfBedsList() throws ServiceException;

    List<NumberOfRooms> getNumberOfRoomsList() throws ServiceException;

    List<Apartment> getApartmentList() throws ServiceException;

    Apartment findApartmentById(int id) throws ServiceException;
    Apartment findApartmentByRoomNumber(int roomNumber) throws ServiceException;

    boolean saveApartment(String apartmentNumber, String floor, String numberOfBedsId, String apartmentTypeId,
                          String numberOfRoomsId, String balcony, String price) throws ServiceException;

    boolean saveApartmentType(String type) throws ServiceException;
    boolean saveNumberOfBeds(int nob) throws ServiceException;
    boolean saveNumberOfRooms(int nor) throws ServiceException;

    boolean editApartment(String id, String apartmentNumber, String floor, String numberOfBedsId, String apartmentTypeId,
                          String numberOfRoomsId, String balcony, String price) throws ServiceException;

    boolean deactivateApartment(int id) throws ServiceException;

    boolean updateApartmentType(int id, String type) throws ServiceException;

    boolean updateNumberOfRooms(int id, int rooms) throws ServiceException;

    boolean updateNumberOfBeds(int id, int beds) throws ServiceException;

    ApartmentType findApartmentTypeById(int id) throws ServiceException;
    NumberOfBeds findNumberOfBedsById(int id) throws ServiceException;
    NumberOfRooms findNumberOfRoomsById(int id) throws ServiceException;
}
