public class DeadlineWrongFormatException extends WrongFormatException {

    public DeadlineWrongFormatException() {
        super("deadline");
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + "description of a task in the " +
                "following format:\ntask /by YYYY-MM-DD hhmm\nwhere hh is hours and mm is minutes.\nPlease ensure " +
                "that the date and time are valid.";
    }
}
