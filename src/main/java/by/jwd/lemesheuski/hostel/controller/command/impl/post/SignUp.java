package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.bean.MessagesSack;
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
            MessagesSack sack = userService.signUp(login, password, password2, name, surname, patronymic);
            if(!sack.getErrors().isEmpty()) {
                for (ErrorMessageName message: sack.getErrors()) {
                    request.setAttribute(message.name(), true);
                }
                request.setAttribute(Params.LOGIN, login);
                request.setAttribute(Params.PASSWORD, password);
                request.setAttribute(Params.PASSWORD2, password2);
                request.setAttribute(Params.SURNAME, surname);
                request.setAttribute(Params.NAME, name);
                request.setAttribute(Params.PATRONYMIC, patronymic);
                return new Router(JspPageName.SIGN_UP_PAGE, RouterType.FORWARD);
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
    }
}
