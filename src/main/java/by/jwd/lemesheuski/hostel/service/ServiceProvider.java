package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.bean.RoomService;
import by.jwd.lemesheuski.hostel.service.impl.ApartmentServiceImpl;
import by.jwd.lemesheuski.hostel.service.impl.OrderServiceImpl;
import by.jwd.lemesheuski.hostel.service.impl.RoomServiceServiceImpl;
import by.jwd.lemesheuski.hostel.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();
    private final ApartmentService apartmentService = new ApartmentServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final RoomServiceService roomServiceService = new RoomServiceServiceImpl();

    public UserService getUserService(){
        return userService;
    }

    public ApartmentService getApartmentService() {
        return apartmentService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public RoomServiceService getRoomServiceService() {
        return roomServiceService;
    }
}
