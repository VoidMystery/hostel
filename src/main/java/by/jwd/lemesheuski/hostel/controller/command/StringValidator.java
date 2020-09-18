package by.jwd.lemesheuski.hostel.controller.command;

public final class StringValidator {
    private static final String INTEGER_PATTERN = "^(-?([1-9][0-9]{0,8}|1[0-9]{9}|20[0-9]{8}|21[0-3][0-9]{7}" +
            "|214[0-6][0-9]{6}|2147[0-3][0-9]{5}|21474[0-7][0-9]{4}|214748[0-2][0-9]{3}|2147483[0-5][0-9]{2}" +
            "|21474836[0-3][0-9]{1}|214748364[0-7])|-2147483648|0)$";
    private static final String DOUBLE_PATTERN = "^(-?[0-9]+(.[0-9]+)?)$";

    public static boolean isStringInteger(String string){
        return string.matches(INTEGER_PATTERN);
    }

    public static boolean isStringDouble(String string){
        return string.matches(DOUBLE_PATTERN);
    }
}

