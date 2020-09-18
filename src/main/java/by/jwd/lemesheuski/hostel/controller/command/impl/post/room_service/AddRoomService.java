package by.jwd.lemesheuski.hostel.controller.command.impl.post.room_service;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.StringValidator;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.RoomServiceService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRoomService implements ICommand {

    private final RoomServiceService roomServiceService = ServiceProvider.getInstance().getRoomServiceService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        if (role != null && role.equals("ROLE_ADMIN")) {
            String roomServiceName = request.getParameter("room_service_name");
            String price = request.getParameter("price");
            if(StringValidator.isStringDouble(price)) {
                try {
                    roomServiceService.saveRoomService(roomServiceName, Double.parseDouble(price));
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }

            }
            return new Router(request.getRequestURI() + "?" + RedirectCommandParam.ALL_ROOM_SERVICES, RouterType.REDIRECT);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
