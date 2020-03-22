package by.jwd.lemesheuski.hostel.controller.command;

public class Validator {
    public static boolean isParametersNull(String ...params){
        for(int i = 0; i < params.length; i++){
            if(params[i] == null){
                return true;
            }
        }
        return false;
    }
}
