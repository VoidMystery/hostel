package by.jwd.lemesheuski.hostel.controller.command.impl.post;

import by.jwd.lemesheuski.hostel.controller.command.ErrorMessageName;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;
import by.jwd.lemesheuski.hostel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements ICommand {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String login = request.getParameter(Params.LOGIN);
        String password = request.getParameter(Params.PASSWORD);
        UserService userService = ServiceProvider.getInstance().getUserService();

        try{
            if(request.getSession().getAttribute(Params.ROLE) == null){

                String role = userService.auth(login, password);

                if (role==null) {
                    request.setAttribute(ErrorMessageName.WRONG_LOGIN_OR_PASSWORD.name(), true);
                    return new Router(JspPageName.LOG_IN_PAGE, RouterType.FORWARD);
                } else {
                    request.getSession().setAttribute("role", role);
                    request.getSession().setAttribute("login", login);
                }
                return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);

            }else{
                return new Router((String) request.getSession().getAttribute("previousGET"), RouterType.REDIRECT);
            }
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
