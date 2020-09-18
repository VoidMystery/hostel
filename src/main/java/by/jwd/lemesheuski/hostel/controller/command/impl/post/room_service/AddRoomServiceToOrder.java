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

public class AddRoomServiceToOrder implements ICommand {

    private final RoomServiceService roomServiceService = ServiceProvider.getInstance().getRoomServiceService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        String orderId = request.getParameter("order_id");
        String roomServiceId = request.getParameter("room_service_id");
        if (role != null && role.equals("ROLE_USER")) {

            if(StringValidator.isStringInteger(orderId) && StringValidator.isStringInteger(roomServiceId)) {
                try {
                    roomServiceService.addRoomServiceToOrder(Integer.parseInt(orderId), Integer.parseInt(roomServiceId));
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }

            }
            return new Router((String) request.getSession().getAttribute("previousGET"), RouterType.REDIRECT);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
