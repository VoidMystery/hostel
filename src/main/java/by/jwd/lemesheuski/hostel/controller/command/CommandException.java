package by.jwd.lemesheuski.hostel.controller.command;

public class CommandException extends Exception{
    private static final long serialVersionUID = 1L;

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
