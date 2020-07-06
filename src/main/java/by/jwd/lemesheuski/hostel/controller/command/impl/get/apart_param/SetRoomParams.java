package by.jwd.lemesheuski.hostel.controller.command.impl.get.apart_param;

import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ApartmentService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetRoomParams implements ICommand {

    private final ApartmentService apartmentService = ServiceProvider.getInstance().getApartmentService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);

        if (role != null && role.equals("ROLE_ADMIN")) {
            request.setAttribute(Params.ROLE, role);
            try {
                request.setAttribute(Params.APARTMENT_TYPES, apartmentService.getApartmentTypeList());
                request.setAttribute(Params.NUMBER_OF_BEDS, apartmentService.getNumberOfBedsList());
                request.setAttribute(Params.NUMBER_OF_ROOMS, apartmentService.getNumberOfRoomsList());
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            return new Router(JspPageName.SET_ROOM_PARAMS_PAGE, RouterType.FORWARD);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
