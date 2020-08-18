public class NotNumberException extends DukeException {
    private static String numberFormatMsg = "My apologies, please key in an appropriate number!";
    public NotNumberException() {
        super(numberFormatMsg);
    }
}
