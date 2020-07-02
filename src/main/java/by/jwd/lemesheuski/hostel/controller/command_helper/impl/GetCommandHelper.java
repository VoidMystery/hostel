package by.jwd.lemesheuski.hostel.controller.command_helper.impl;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.*;
import by.jwd.lemesheuski.hostel.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.apartment.AddApartment;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.apartment.Apartments;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.order.SelectApartments;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.order.SelectApartmentsWithDate;
import by.jwd.lemesheuski.hostel.controller.command.impl.get.order.ShoppingCart;
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
        commands.put(CommandName.SET_ROOM_PARAMS, new SetRoomParams());
        commands.put(CommandName.APARTMENTS, new Apartments());
        commands.put(CommandName.ADD_APARTMENT, new AddApartment());
        commands.put(CommandName.SELECT_APARTMENTS, new SelectApartments());
        commands.put(CommandName.SELECT_APARTMENTS_WITH_DATE, new SelectApartmentsWithDate());
        commands.put(CommandName.SHOPPING_CART, new ShoppingCart());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
