package by.jwd.lemesheuski.hostel.controller.command.impl;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoSuchCommand implements ICommand {

    private static final Logger log = Logger.getLogger(NoSuchCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        log.info(request.getParameter("command"));
        return new Router(JspPageName.ERROR_PAGE, RouterType.REDIRECT);
    }
}
