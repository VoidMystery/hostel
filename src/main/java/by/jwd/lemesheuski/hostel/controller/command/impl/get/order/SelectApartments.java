package by.jwd.lemesheuski.hostel.controller.command.impl.get.order;

import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectApartments implements ICommand {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String) request.getSession().getAttribute(Params.ROLE);
        request.setAttribute(Params.ROLE, role);
        return new Router(JspPageName.SELECT_APARTMENTS, RouterType.FORWARD);
    }
}
