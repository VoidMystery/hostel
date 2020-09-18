package by.jwd.lemesheuski.hostel.controller.command_helper.impl;

import by.jwd.lemesheuski.hostel.controller.command.ICommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.*;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apart_param.*;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment.DeactivateApartment;
import by.jwd.lemesheuski.hostel.controller.command.impl.NoSuchCommand;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment.AddApartment;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.apartment.EditApartment;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.order.ConfirmPayment;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.order.Reservation;
import by.jwd.lemesheuski.hostel.controller.command.impl.post.room_service.*;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.hostel.controller.command_helper.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class PostCommandHelper implements CommandHelper {
    private Map<CommandName, ICommand> commands = new HashMap<>();
    public PostCommandHelper() {
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.LOG_IN, new LogIn());
        commands.put(CommandName.PASSWORD_CHANGE, new PasswordChange());
        commands.put(CommandName.LOG_OUT, new LogOut());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.LOCAL_CHANGE, new LocalChange());
        commands.put(CommandName.ADD_APARTMENT, new AddApartment());
        commands.put(CommandName.RESERVATION, new Reservation());
        commands.put(CommandName.ADD_TYPE, new AddType());
        commands.put(CommandName.ADD_NOR, new AddNumberOfRoom());
        commands.put(CommandName.ADD_NOB, new AddNumberOfBed());
        commands.put(CommandName.CONFIRM_PAYMENT, new ConfirmPayment());
        commands.put(CommandName.EDIT_APARTMENT, new EditApartment());
        commands.put(CommandName.DEACTIVATE_APARTMENT, new DeactivateApartment());
        commands.put(CommandName.ADD_ROOM_SERVICE, new AddRoomService());
        commands.put(CommandName.ADD_ROOM_SERVICE_TO_ORDER, new AddRoomServiceToOrder());
        commands.put(CommandName.EDIT_ROOM_SERVICE, new EditRoomService());
        commands.put(CommandName.DEACTIVATE_ROOM_SERVICE, new DeactivateRoomService());
        commands.put(CommandName.CONFIRM_SERVICE_PAYMENT, new ConfirmServicePayment());
    }
    public ICommand getCommand(String commandName) {
        return getICommand(commandName, commands);
    }
}
