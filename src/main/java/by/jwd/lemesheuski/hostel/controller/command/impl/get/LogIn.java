package by.jwd.lemesheuski.hostel.controller.command.impl.get;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command_helper.JspPageName;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final String WRONG_LOGIN_OR_PASSWORD = "WRONG_LOGIN_OR_PASSWORD";
        if(request.getSession().getAttribute("role")==null) {
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(WRONG_LOGIN_OR_PASSWORD)){
                    request.setAttribute(WRONG_LOGIN_OR_PASSWORD, cookie.getValue());
                }
            }
            return JspPageName.LOG_IN_PAGE;
        }
        return JspPageName.MAIN_PAGE;
    }
}
