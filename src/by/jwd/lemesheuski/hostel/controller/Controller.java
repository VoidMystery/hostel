package by.jwd.lemesheuski.hostel.controller;

import by.jwd.lemesheuski.hostel.controller.command.*;
import by.jwd.lemesheuski.hostel.controller.command_helper.*;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.CommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.GetCommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.PostCommandHelper;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

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

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().initPoolData();
        }catch (ConnectionPoolException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("command")!=null) {
            GetCommandHelper getCommandHelper = CommandHelperProvider.getInstance().getGetCommandHelper();
            requestHandler(getCommandHelper, request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.MAIN_PAGE);
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostCommandHelper postCommandHelper = CommandHelperProvider.getInstance().getPostCommandHelper();
        requestHandler(postCommandHelper, request, response);
    }

    private void requestHandler(CommandHelper commandHelper, HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException{
        String commandName;
        if(request.getParameter("command")==null && request.getMethod().equals("GET")) {
            commandName = "start";
            request.setAttribute("path", request.getPathInfo());
        }else {
            commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        }
        ICommand command = commandHelper.getCommand(commandName);
        String page = null;
        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            page = JspPageName.ERROR_PAGE;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher == null) {
            page = JspPageName.ERROR_PAGE;
            dispatcher = request.getRequestDispatcher(page);
        }
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
        super.destroy();
    }
}
