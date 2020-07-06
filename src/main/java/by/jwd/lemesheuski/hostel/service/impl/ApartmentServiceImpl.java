package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.ApartmentType;
import by.jwd.lemesheuski.hostel.bean.NumberOfBeds;
import by.jwd.lemesheuski.hostel.bean.NumberOfRooms;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment.EditApartment;
import by.jwd.lemesheuski.hostel.dao.ApartmentDao;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.service.ApartmentService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class ApartmentServiceImpl implements ApartmentService {
    private static final Logger log = Logger.getLogger(ApartmentServiceImpl.class);

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
        apartment.setFloor(Integer.parseInt(floor));
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

    @Override
    public boolean updateApartmentType(int id, String type) throws ServiceException {
        ApartmentType apartmentType = new ApartmentType();
        apartmentType.setId(id);
        apartmentType.setType(type);
        try{
            return apartmentDao.updateApartmentType(apartmentType);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateNumberOfRooms(int id, int rooms) throws ServiceException {
        NumberOfRooms numberOfRooms = new NumberOfRooms();
        numberOfRooms.setId(id);
        numberOfRooms.setRooms(rooms);
        try{
            return apartmentDao.updateNumberOfRooms(numberOfRooms);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateNumberOfBeds(int id, int beds) throws ServiceException {
        NumberOfBeds numberOfBeds = new NumberOfBeds();
        numberOfBeds.setId(id);
        numberOfBeds.setBeds(beds);
        try{
            return apartmentDao.updateNumberOfBeds(numberOfBeds);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public ApartmentType findApartmentTypeById(int id) throws ServiceException {
        ApartmentType apartmentType;
        try{
            apartmentType = apartmentDao.findApartmentTypeById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return apartmentType;
    }

    @Override
    public NumberOfBeds findNumberOfBedsById(int id) throws ServiceException {
        NumberOfBeds numberOfBeds;
        try{
            numberOfBeds = apartmentDao.findNumberOfBedsById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return numberOfBeds;
    }

    @Override
    public NumberOfRooms findNumberOfRoomsById(int id) throws ServiceException {
        NumberOfRooms numberOfRooms;
        try{
            numberOfRooms = apartmentDao.findNumberOfRoomsById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return numberOfRooms;
    }

    @Override
    public Apartment findApartmentById(int id) throws ServiceException {
        Apartment apartment;
        try{
            apartment = apartmentDao.findApartmentById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return apartment;
    }

    @Override
    public boolean editApartment(String id, String apartmentNumber, String floor, String numberOfBedsId,
                                 String apartmentTypeId, String numberOfRoomsId,
                                 String balcony, String price) throws ServiceException{
        Apartment apartment = new Apartment();
        NumberOfRooms numberOfRooms = new NumberOfRooms();
        NumberOfBeds numberOfBeds = new NumberOfBeds();
        ApartmentType apartmentType = new ApartmentType();

        apartment.setId(Integer.parseInt(id));
        apartment.setRoomNumber(Integer.parseInt(apartmentNumber));
        apartment.setFloor(Integer.parseInt(floor));
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
            return apartmentDao.updateApartment(apartment, apartmentType, numberOfBeds, numberOfRooms);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
