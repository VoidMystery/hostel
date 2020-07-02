package by.jwd.lemesheuski.hostel.controller;

import by.jwd.lemesheuski.hostel.controller.command.*;
import by.jwd.lemesheuski.hostel.controller.command.impl.Params;
import by.jwd.lemesheuski.hostel.controller.command_helper.*;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.GetCommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.PostCommandHelper;
import by.jwd.lemesheuski.hostel.controller.router.Router;
import by.jwd.lemesheuski.hostel.controller.router.RouterType;
import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Controller.class);

    public Controller() {
        super();
        log.info("Logging is running");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       final String PREVIOUS_GET = "previousGET";

        GetCommandHelper getCommandHelper = CommandHelperProvider.getInstance().getGetCommandHelper();
        request.getSession().setAttribute(PREVIOUS_GET, request.getRequestURI() + "?" + request.getQueryString());

        requestHandler(getCommandHelper, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostCommandHelper postCommandHelper = CommandHelperProvider.getInstance().getPostCommandHelper();

        requestHandler(postCommandHelper, request, response);
    }

    private void requestHandler(CommandHelper commandHelper, HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException{
        request.setAttribute(Params.ROLE, request.getSession().getAttribute(Params.ROLE));
        request.setAttribute(Params.LOGIN, request.getSession().getAttribute(Params.LOGIN));

        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = commandHelper.getCommand(commandName);
        Router router;
        try {
            router = command.execute(request, response);
        } catch (CommandException e) {
            log.error(e);
            router = new Router(JspPageName.ERROR_PAGE, RouterType.REDIRECT);
        }

        if(router.getType().equals(RouterType.FORWARD)){
            String page = router.getRoute();
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            if (dispatcher == null) {
                page = JspPageName.ERROR_PAGE;
                dispatcher = request.getRequestDispatcher(page);
            }
            dispatcher.forward(request, response);
        }

        if(router.getType().equals(RouterType.REDIRECT)){
            response.sendRedirect(router.getRoute());
        }
    }


}
