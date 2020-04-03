package by.jwd.lemesheuski.hostel.controller.command_helper;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandName;

import java.util.Map;

public interface CommandHelper {
    ICommand getCommand(String commandName);

    default ICommand getICommand(String commandName, Map<CommandName, ICommand> commands) {
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