package by.jwd.lemesheuski.hostel.service.validator;

public final class UserServiceValidator {
    private static final String LOGIN_PATTERN = "[a-zA-Z1-9_]{3,20}";
    private static final String PASSWORD_PATTERN = "[a-zA-Z1-9_]{5,20}";
    private static final String SNP_PATTERN = "([A-Z][a-z]{2,15})|([А-Я][а-я]{2,15})";

    private UserServiceValidator(){}

    public static boolean isSingUpParamsValid(String login, String password, String password2, String surname, String name,
                                               String patronymic){
        boolean isValid = true;

        if (login == null || !login.matches(LOGIN_PATTERN) || password == null || password2 == null ||
                !password.matches(PASSWORD_PATTERN) || !password2.equals(password) || surname == null ||
                !surname.matches(SNP_PATTERN) || name == null || !name.matches(SNP_PATTERN) || patronymic == null ||
                !patronymic.matches(SNP_PATTERN)) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isPasswordValid(String password){
        return password.matches(PASSWORD_PATTERN);
    }
}
