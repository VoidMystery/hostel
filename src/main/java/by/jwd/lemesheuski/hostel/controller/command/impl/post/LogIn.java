package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.controller.command.Validator;
import by.jwd.lemesheuski.hostel.controller.command_helper.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import by.jwd.lemesheuski.hostel.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final String WRONG_LOGIN_OR_PASSWORD = "WRONG_LOGIN_OR_PASSWORD";

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (Validator.isParametersNull(login, password)){
            return (String) request.getSession().getAttribute("previousGET");
        }
        UserService userService = ServiceProvider.getInstance().getUserService();

        try{
            if(request.getSession().getAttribute("role") == null){
                String role = userService.auth(login, password);

                if (role==null) {
                    Cookie login_error_message = new Cookie(WRONG_LOGIN_OR_PASSWORD, Boolean.toString(true));
                    login_error_message.setMaxAge(1);
                    response.addCookie(login_error_message);
                } else {
                    request.getSession().setAttribute("role", role);
                    request.getSession().setAttribute("login", login);
                }

                return (String) request.getSession().getAttribute("previousGET");
            }
        }catch (Exception e){
            throw new CommandException(e);
        }
        return JspPageName.ERROR_PAGE;
    }
}
