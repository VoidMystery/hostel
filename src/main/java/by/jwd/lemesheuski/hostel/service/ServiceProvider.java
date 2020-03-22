package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();

    public UserService getUserService(){
        return userService;
    }
}
