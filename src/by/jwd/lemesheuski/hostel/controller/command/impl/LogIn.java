package by.jwd.lemesheuski.hostel.controller.command.impl;

import by.jwd.lemesheuski.hostel.controller.command.Validator;
import by.jwd.lemesheuski.hostel.controller.command_helper.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import by.jwd.lemesheuski.hostel.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LogIn implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (Validator.isParametersNull(login, password)){
            return JspPageName.ERROR_PAGE;
        }

        UserService userService = ServiceProvider.getInstance().getUserService();

        try{
            request.setAttribute("role", userService.auth(login, password));

        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return JspPageName.LOGIN_PAGE;
    }
}
