package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.ApartmentType;
import by.jwd.lemesheuski.hostel.bean.NumberOfBeds;
import by.jwd.lemesheuski.hostel.bean.NumberOfRooms;
import by.jwd.lemesheuski.hostel.dao.ApartmentDao;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.service.ApartmentService;
import by.jwd.lemesheuski.hostel.service.ServiceException;

import java.util.List;

public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentDao apartmentDao = DAOProvider.getInstance().getApartmentDAO();

    @Override
    public List<ApartmentType> getApartmentTypeList() throws ServiceException {
        List<ApartmentType> apartmentTypeList;
        try {
            apartmentTypeList = apartmentDao.findAllApartmentTypes();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return apartmentTypeList;
    }

    @Override
    public List<NumberOfBeds> getNumberOfBedsList() throws ServiceException {
        List<NumberOfBeds> numberOfBedsList;
        try {
            numberOfBedsList = apartmentDao.findAllNumberOfBeds();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return numberOfBedsList;
    }

    @Override
    public List<NumberOfRooms> getNumberOfRoomsList() throws ServiceException {
        List<NumberOfRooms> numberOfRoomsList;
        try {
            numberOfRoomsList = apartmentDao.findAllNumberOfRooms();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return numberOfRoomsList;
    }

    @Override
    public List<Apartment> getApartmentList() throws ServiceException {
        List<Apartment> apartmentList;
        try {
            apartmentList = apartmentDao.findAllApartments();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return apartmentList;
    }

    @Override
    public boolean saveApartment(String apartmentNumber, String floor, String numberOfBedsId, String apartmentTypeId,
                                 String numberOfRoomsId, String balcony, String price) throws ServiceException {
        Apartment apartment = new Apartment();
        NumberOfRooms numberOfRooms = new NumberOfRooms();
        NumberOfBeds numberOfBeds = new NumberOfBeds();
        ApartmentType apartmentType = new ApartmentType();

        apartment.setRoomNumber(Integer.parseInt(apartmentNumber));
        if(balcony==null){
            apartment.setBalcony(false);
        }else if(balcony.equals("on")){
            apartment.setBalcony(true);
        }
        apartment.setPrice(Double.parseDouble(price));

        numberOfBeds.setId(Integer.parseInt(numberOfBedsId));
        numberOfRooms.setId(Integer.parseInt(numberOfRoomsId));
        apartmentType.setId(Integer.parseInt(apartmentTypeId));
        try {
            return apartmentDao.saveApartment(apartment, apartmentType, numberOfBeds, numberOfRooms);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
