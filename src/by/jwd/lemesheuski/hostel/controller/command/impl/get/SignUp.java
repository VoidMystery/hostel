package by.jwd.lemesheuski.hostel.controller.command.impl.get;

import by.jwd.lemesheuski.hostel.controller.command.CommandException;
import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command_helper.JspPageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return JspPageName.SIGN_UP_PAGE;
    }
}
