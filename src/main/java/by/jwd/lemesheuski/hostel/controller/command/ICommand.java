package by.jwd.lemesheuski.hostel.controller.command;

import by.jwd.lemesheuski.hostel.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
