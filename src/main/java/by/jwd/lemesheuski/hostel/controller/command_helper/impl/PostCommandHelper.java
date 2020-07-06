package by.jwd.lemesheuski.hostel.controller.command_helper.impl;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LocalChange;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LogIn;
import by.jwd.lemesheuski.hostel.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.LogOut;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.SignUp;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apart_param.EditNumberOfBed;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apart_param.EditNumberOfRoom;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apart_param.EditType;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment.AddApartment;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment.EditApartment;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.order.ConfirmPayment;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.order.Reservation;
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
        commands.put(CommandName.ADD_APARTMENT, new AddApartment());
        commands.put(CommandName.RESERVATION, new Reservation());
        commands.put(CommandName.EDIT_TYPE, new EditType());
        commands.put(CommandName.EDIT_NUMBER_OF_ROOM, new EditNumberOfRoom());
        commands.put(CommandName.EDIT_NUMBER_OF_BED, new EditNumberOfBed());
        commands.put(CommandName.CONFIRM_PAYMENT, new ConfirmPayment());
        commands.put(CommandName.EDIT_APARTMENT, new EditApartment());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
