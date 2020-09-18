package by.jwd.lemesheuski.hostel.controller.command_helper;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;

import java.util.Map;

public interface CommandHelper {
    ICommand getCommand(String commandName);

    default ICommand getICommand(String commandName, Map<CommandName, ICommand> commands) {
        ICommand command;

        if (commandName != null) {
            try {
                CommandName name = CommandName.valueOf(commandName.toUpperCase());
                command = commands.get(name);
                if(command == null){
                    command = commands.get(CommandName.NO_SUCH_COMMAND);
                }
            } catch (IllegalArgumentException e) {
                command = commands.get(CommandName.NO_SUCH_COMMAND);
            }
        }else{
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }

        return command;
    }
}
