package by.jwd.lemesheuski.hostel.controller.command_helper.impl;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.LogIn;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.Main;
import by.jwd.lemesheuski.hostel.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.Profile;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.SignUp;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class GetCommandHelper implements CommandHelper {
    private Map<CommandName, ICommand> commands = new HashMap<>();
    public GetCommandHelper() {
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.MAIN, new Main());
        commands.put(CommandName.PROFILE, new Profile());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.LOG_IN, new LogIn());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
