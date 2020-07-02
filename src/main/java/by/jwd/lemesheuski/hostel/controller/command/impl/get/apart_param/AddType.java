package by.jwd.lemesheuski.hostel.controller.command.impl.get.apart_param;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.service.ApartmentService;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddType implements ICommand {

    private final ApartmentService apartmentService = ServiceProvider.getInstance().getApartmentService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        return null;
    }
}
