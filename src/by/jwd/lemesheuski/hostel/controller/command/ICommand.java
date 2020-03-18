package by.jwd.lemesheuski.hostel.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    public String execute(HttpServletRequest request) throws CommandException;
}
