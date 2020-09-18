package by.jwd.lemesheuski.hostel.controller.command.impl.get.room_service;

import by.jwd.lemesheuski.hostel.controller.JspPageName;
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

public class EditRoomService implements ICommand {

    private final RoomServiceService roomServiceService = ServiceProvider.getInstance().getRoomServiceService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        String id = request.getParameter("id");
        if (role!=null && role.equals("ROLE_ADMIN")) {
            request.setAttribute(Params.ROLE, role);
            if(StringValidator.isStringInteger(id)) {
                try {
                    request.setAttribute(Params.ROOM_SERVICE, roomServiceService.findRoomServiceById(Integer.parseInt(id)));
                }catch (ServiceException e){
                    throw new CommandException(e);
                }
                return new Router(JspPageName.EDIT_ROOM_SERVICE, RouterType.FORWARD);
            }
            return new Router((String) request.getSession().getAttribute("previousGET"), RouterType.REDIRECT);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }

}
