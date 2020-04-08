package by.jwd.lemesheuski.hostel.controller.command.impl.get;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.JspPageName;
import by.jwd.lemesheuski.hostel.controller.command.RedirectCommandParam;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Profile implements ICommand {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String)request.getSession().getAttribute(Params.ROLE);

        if(role!=null){
            request.setAttribute(Params.ROLE, role);
            try {
                request.setAttribute(Params.USER, ServiceProvider.getInstance()
                        .getUserService()
                        .getUserInfo((String) request.getSession().getAttribute(Params.LOGIN)));
            }catch (ServiceException e){
                throw new CommandException(e);
            }
            return new Router(JspPageName.PROFILE_PAGE, RouterType.FORWARD);
        }else{
            return new Router(request.getRequestURI() + "?" + RedirectCommandParam.MAIN_PAGE, RouterType.REDIRECT);
        }

    }
}
