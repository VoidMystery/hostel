package by.jwd.lemesheuski.hostel.bean;

import by.jwd.lemesheuski.hostel.controller.command.ErrorMessageName;

import java.util.ArrayList;
import java.util.List;

public class MessagesSack {
    List<ErrorMessageName> errors;

    public MessagesSack() {
        this.errors = new ArrayList<>();
    }

    public void addError(ErrorMessageName errorMessageName){
        errors.add(errorMessageName);
    }

    public List<ErrorMessageName> getErrors() {
        return new ArrayList<>(errors);
    }
}
