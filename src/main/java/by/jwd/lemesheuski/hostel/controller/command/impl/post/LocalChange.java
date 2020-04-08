package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalChange implements ICommand {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String local = request.getParameter("local");

        request.getSession().setAttribute("local", local);

        return new Router((String) request.getSession().getAttribute("previousGET"), RouterType.REDIRECT);
    }
}
