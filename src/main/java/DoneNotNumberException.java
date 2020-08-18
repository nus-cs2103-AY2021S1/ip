public class DoneNotNumberException extends DukeException {
    private static String numberFormatMsg = "Please key in an appropriate number!";
    public DoneNotNumberException() {
        super(numberFormatMsg);
    }
}
