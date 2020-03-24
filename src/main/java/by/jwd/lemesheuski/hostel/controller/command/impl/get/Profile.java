package by.jwd.lemesheuski.hostel.controller.command.impl.get;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command_helper.JspPageName;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Profile implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String role = (String)request.getSession().getAttribute("role");
        if(role!=null){
            request.setAttribute("role", role);
            try {
                request.setAttribute("user", ServiceProvider.getInstance()
                        .getUserService()
                        .getUserInfo((String) request.getSession().getAttribute("login")));
            }catch (ServiceException e){
                throw new CommandException(e);
            }
            return JspPageName.PROFILE_PAGE;
        }else{
            return JspPageName.MAIN_PAGE;
        }

    }
}
