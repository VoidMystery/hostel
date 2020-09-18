package by.jwd.lemesheuski.hostel.service.validator;

public class ApartmentServiceValidator {
    private static final String ROOM_PATTERN= "([2][0][0-1][0-9])|([2][0][2][0])|([1][0-9][0-1][0-9])|" +
            "([1][0-9][2][0])|([1-9][0-1][0-9])|([1-9][2][0])";


    private ApartmentServiceValidator(){
    }

    public static boolean roomNumberValidation(String string){
        return  string.matches(ROOM_PATTERN);
    }
    public static boolean isIntPositive(int a){
        return a>0;
    }
    public static boolean isDoublePositive(double a){
        return a>0;
    }
    public static boolean isFloorValid(int a){
        return a > 0 && a < 21;
    }

    public static boolean isApartmentValid(String apartmentNumber, int floor, int numberOfBedsId, int apartmentTypeId,
                                     int numberOfRoomsId, double price){
        return ApartmentServiceValidator.roomNumberValidation(apartmentNumber)&&
                ApartmentServiceValidator.isIntPositive(numberOfBedsId)&&
                ApartmentServiceValidator.isIntPositive(apartmentTypeId)&&
                ApartmentServiceValidator.isIntPositive(numberOfRoomsId)&&
                ApartmentServiceValidator.isDoublePositive(price)&&
                ApartmentServiceValidator.isFloorValid(floor);
    }
}
