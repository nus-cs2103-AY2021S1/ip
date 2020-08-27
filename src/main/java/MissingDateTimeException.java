public class MissingDateTimeException extends DukeException {

    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! The command you keyed in needs to have a timing!\n" +
                "     The timing should be typed in after the tag, in the format \"DD/MM/YYYY\" HHMM!";
    }
}