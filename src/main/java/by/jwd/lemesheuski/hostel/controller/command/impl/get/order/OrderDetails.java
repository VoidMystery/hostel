package by.jwd.lemesheuski.hostel.controller.command.impl.get.order;

import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.StringValidator;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.OrderService;
import by.jwd.lemesheuski.hostel.service.RoomServiceService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderDetails implements ICommand {

    private final OrderService orderService = ServiceProvider.getInstance().getOrderService();
    private final RoomServiceService roomServiceService = ServiceProvider.getInstance().getRoomServiceService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        String id = request.getParameter("id");
        if (role != null) {
            request.setAttribute(Params.ROLE, role);
            if (StringValidator.isStringInteger(id)) {
                try {
                    Order order = orderService.findOrderById(Integer.parseInt(id));
                    if(order != null) {
                        request.setAttribute(Params.ORDER, order);
                        request.setAttribute(Params.ROOM_SERVICES, roomServiceService.findRoomServicesByOrderId(Integer.parseInt(id)));
                        request.setAttribute(Params.ALL_ROOM_SERVICES, roomServiceService.findAllRoomService());
                        return new Router(JspPageName.ORDER, RouterType.FORWARD);
                    }
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }

            }
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
