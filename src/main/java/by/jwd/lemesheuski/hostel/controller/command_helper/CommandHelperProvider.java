package by.jwd.lemesheuski.hostel.controller.command_helper;

import by.jwd.lemesheuski.hostel.controller.command_helper.impl.GetCommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.impl.PostCommandHelper;

public class CommandHelperProvider {
    private static final CommandHelperProvider instance = new CommandHelperProvider();
    private CommandHelperProvider(){}

    public static CommandHelperProvider getInstance() {
        return instance;
    }

    private final GetCommandHelper getCommandHelper = new GetCommandHelper();
    private final PostCommandHelper postCommandHelper = new PostCommandHelper();

    public GetCommandHelper getGetCommandHelper() {
        return getCommandHelper;
    }

    public PostCommandHelper getPostCommandHelper() {
        return postCommandHelper;
    }
}
