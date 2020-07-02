package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.controller.command.ErrorMessageName;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import by.jwd.lemesheuski.hostel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(Params.LOGIN);
        String password = request.getParameter(Params.PASSWORD);
        String password2 = request.getParameter(Params.PASSWORD2);
        String name = request.getParameter(Params.NAME);
        String surname = request.getParameter(Params.SURNAME);
        String patronymic = request.getParameter(Params.PATRONYMIC);

        UserService userService = ServiceProvider.getInstance().getUserService();
        
        try {
            if (userService.getUserInfo(login) != null) {
                request.setAttribute(ErrorMessageName.LOGIN_IS_PRESENT.name(), true);
            }else {

                if (userService.signUp(login, password, password2, name, surname, patronymic) == 0) {
                    request.setAttribute(ErrorMessageName.WRONG_DATA.name(), true);
                }else{
                    return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
                }

            }
            request.setAttribute(Params.LOGIN, login);
            request.setAttribute(Params.PASSWORD, password);
            request.setAttribute(Params.PASSWORD2, password2);
            request.setAttribute(Params.SURNAME, surname);
            request.setAttribute(Params.NAME, name);
            request.setAttribute(Params.PATRONYMIC, patronymic);
            return new Router(JspPageName.SIGN_UP_PAGE, RouterType.FORWARD);

        }catch (ServiceException e){
            throw new CommandException(e);
        }
    }
}
