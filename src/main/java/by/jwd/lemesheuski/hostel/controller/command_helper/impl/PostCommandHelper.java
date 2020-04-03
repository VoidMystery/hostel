package by.jwd.lemesheuski.hostel.controller.command_helper.impl;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LocalChange;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LogIn;
import by.jwd.lemesheuski.hostel.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LogOut;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.SignUp;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class PostCommandHelper implements CommandHelper {
    private Map<CommandName, ICommand> commands = new HashMap<>();
    public PostCommandHelper() {
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.LOG_IN, new LogIn());
        commands.put(CommandName.LOG_OUT, new LogOut());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.LOCAL_CHANGE, new LocalChange());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
