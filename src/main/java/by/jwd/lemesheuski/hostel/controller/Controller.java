package by.jwd.lemesheuski.hostel.controller;

import by.jwd.lemesheuski.hostel.controller.command.*;
import by.jwd.lemesheuski.hostel.controller.command_helper.*;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.GetCommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.PostCommandHelper;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;

        GetCommandHelper getCommandHelper = CommandHelperProvider.getInstance().getGetCommandHelper();
        request.getSession().setAttribute("previousGET", request.getRequestURI() + "?" + request.getQueryString());

        page = requestHandler(getCommandHelper, request, response);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher == null) {
            page = JspPageName.ERROR_PAGE;
            dispatcher = request.getRequestDispatcher(page);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;

        PostCommandHelper postCommandHelper = CommandHelperProvider.getInstance().getPostCommandHelper();

        page = requestHandler(postCommandHelper, request, response);

        response.sendRedirect(page);
    }

    private String requestHandler(CommandHelper commandHelper, HttpServletRequest request,
                                HttpServletResponse response){
        request.setAttribute("role", request.getSession().getAttribute("role"));
        request.setAttribute("login", request.getSession().getAttribute("login"));
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = commandHelper.getCommand(commandName);
        String page;
        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            page = JspPageName.ERROR_PAGE;
        }
        return page;
    }


}
