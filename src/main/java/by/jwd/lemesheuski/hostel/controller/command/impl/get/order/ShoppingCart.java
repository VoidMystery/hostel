package by.jwd.lemesheuski.hostel.controller.command.impl.get.order;

import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ApartmentService;
import by.jwd.lemesheuski.hostel.service.OrderService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShoppingCart implements ICommand {
    private final OrderService orderService = ServiceProvider.getInstance().getOrderService();
    private final ApartmentService apartmentService = ServiceProvider.getInstance().getApartmentService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        List<Order> orderList;

        if (role.equals("ROLE_USER")) {
            try {
                orderList=orderService.findOrdersByLogin((String)request.getSession().getAttribute(Params.LOGIN));
                request.setAttribute(Params.ORDERS, orderList);
            }catch (ServiceException e){
                throw new CommandException(e);
            }
            return new Router(JspPageName.ORDERS, RouterType.FORWARD);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
