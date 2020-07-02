package by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment;

import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ApartmentService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddApartment implements ICommand {
    private final ApartmentService apartmentService = ServiceProvider.getInstance().getApartmentService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        if (role.equals("ROLE_ADMIN")) {
            String apartmentNumber = request.getParameter("apartment_number");
            String floor = request.getParameter("floor");
            String numberOfBedsId = request.getParameter("number_of_beds");
            String apartmentTypeId = request.getParameter("apartment_type");
            String numberOfRoomsId = request.getParameter("number_of_rooms");
            String balcony = request.getParameter("balcony");
            String price = request.getParameter("price");
            try {
                apartmentService.saveApartment(apartmentNumber,floor,numberOfBedsId,apartmentTypeId,numberOfRoomsId,
                        balcony,price);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return new Router((String) request.getSession().getAttribute("previousGET"), RouterType.REDIRECT);
    }
}
