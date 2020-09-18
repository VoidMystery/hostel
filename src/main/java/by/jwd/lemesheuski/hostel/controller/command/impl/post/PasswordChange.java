package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.controller.command.*;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import by.jwd.lemesheuski.hostel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordChange implements ICommand {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String role = (String) request.getSession().getAttribute(Params.ROLE);
        String currentPassword = request.getParameter("current_pass");
        String password = request.getParameter("new_pass");
        String password2 = request.getParameter("new_pass2");

        if (role != null) {
            request.setAttribute(Params.ROLE, role);
            if (currentPassword != null && password != null && password2 != null) {
                try {
                    userService.updateUserPasswordByLogin((String) request.getSession().getAttribute("login"),
                            currentPassword, password, password2);
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            }
            return new Router(request.getRequestURI() + "?" + RedirectCommandParam.PROFILE, RouterType.REDIRECT);
        }

        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
