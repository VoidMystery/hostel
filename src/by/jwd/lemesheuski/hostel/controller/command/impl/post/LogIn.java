package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.controller.command.Validator;
import by.jwd.lemesheuski.hostel.controller.command_helper.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import by.jwd.lemesheuski.hostel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String message = "Неправильный логин или пароль";
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (Validator.isParametersNull(login, password)){
            return JspPageName.ERROR_PAGE;
        }

        UserService userService = ServiceProvider.getInstance().getUserService();

        try{
            String role = userService.auth(login, password);
            if(role.equals("guest")) {
                request.setAttribute("message", message);
            }else {
                request.getSession().setAttribute("role", role);
                request.setAttribute("role", role);
                return JspPageName.PROFILE_PAGE;
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return JspPageName.MAIN_PAGE;
    }
}
