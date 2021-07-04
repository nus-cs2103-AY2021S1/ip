public class DukeWrongFormattingException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Sorry! The format for a date and time is: dd-mm-yyyy HHMM";
    }
}
