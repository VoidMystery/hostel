package by.jwd.lemesheuski.hostel.controller.command.impl.post.order;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.StringValidator;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.OrderService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPayment implements ICommand {
    private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        String id = request.getParameter("id");

        if (role != null && role.equals("ROLE_ADMIN")) {
            request.setAttribute(Params.ROLE, role);
            if(StringValidator.isStringInteger(id)) {
                try {
                    orderService.updateOrderStatusById(Integer.parseInt(id));
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            }
            return new Router(request.getRequestURI() + "?" + RedirectCommandParam.ORDERS, RouterType.REDIRECT);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
