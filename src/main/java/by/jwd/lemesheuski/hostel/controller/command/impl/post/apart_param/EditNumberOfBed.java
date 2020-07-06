package by.jwd.lemesheuski.hostel.controller.command.impl.post.apart_param;

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

public class EditNumberOfBed implements ICommand {
    private final ApartmentService apartmentService = ServiceProvider.getInstance().getApartmentService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        String id = request.getParameter("id");
        String beds = request.getParameter("beds");
        if (role!=null && role.equals("ROLE_ADMIN")) {
            request.setAttribute(Params.ROLE, role);
            try {
                apartmentService.updateNumberOfBeds(Integer.parseInt(id), Integer.parseInt(beds));
            } catch (ServiceException e) {

                throw new CommandException(e);
            }
            return new Router(request.getRequestURI() + "?" + RedirectCommandParam.ROOM_PARAMS, RouterType.REDIRECT);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}