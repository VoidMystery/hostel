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

    boolean saveApartment(String apartmentNumber, String floor, String numberOfBedsId, String apartmentTypeId,
                          String numberOfRoomsId, String balcony, String price) throws ServiceException;
}
