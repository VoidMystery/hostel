package by.jwd.lemesheuski.hostel.controller.command_helper.impl;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LogIn;
import by.jwd.lemesheuski.hostel.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LogOut;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.SignUp;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class PostCommandHelper implements  CommandHelper{
    private Map<CommandName, ICommand> commands = new HashMap<>();
    public PostCommandHelper() {
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.LOGIN, new LogIn());
        commands.put(CommandName.LOG_OUT, new LogOut());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }
    public ICommand getCommand(String commandName) {
        ICommand command = null;

        if (commandName != null) {
            try {
                CommandName name = CommandName.valueOf(commandName.toUpperCase());
                command = commands.get(name);
            } catch (IllegalArgumentException e) {
                command = commands.get(CommandName.NO_SUCH_COMMAND);
            }
        }

        if(command == null) {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }

        return command;
    }
}
