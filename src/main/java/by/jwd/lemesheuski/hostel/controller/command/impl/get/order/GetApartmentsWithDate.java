package by.jwd.lemesheuski.hostel.controller.command.impl.get.order;

import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.OrderService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class GetApartmentsWithDate implements ICommand {
    private final OrderService orderService= ServiceProvider.getInstance().getOrderService();
    private static final Logger log = Logger.getLogger(GetApartmentsWithDate.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        if (!role.equals("ROLE_ADMIN")) {
            String beginningDateStr = request.getParameter("beginning_date");
            String endDateStr = request.getParameter("end_date");
            LocalDate beginningDate = LocalDate.parse(beginningDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            try {
                request.setAttribute(Params.APARTMENTS, orderService.findApartmentByDate(beginningDate, endDate));
                request.setAttribute(Params.BEGINNING_DATE, beginningDateStr);
                request.setAttribute(Params.END_DATE, endDateStr);
            } catch (ServiceException e) {

                throw new CommandException(e);
            }
            return new Router(JspPageName.APARTMENT_PAGE, RouterType.FORWARD);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
