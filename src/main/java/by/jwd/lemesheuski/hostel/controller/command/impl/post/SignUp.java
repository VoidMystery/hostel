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

public class SignUp implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");

        if (Validator.isParametersNull(login, password, password2, name, surname, patronymic)){
            return JspPageName.ERROR_PAGE;
        }

        UserService userService = ServiceProvider.getInstance().getUserService();

        try {
            request.setAttribute("message", userService.signUp(login, password, password2, name, surname, patronymic));
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return (String) request.getSession().getAttribute("previousGET");
    }
}
