public class DukeInvalidTimeException extends DukeException {
    public DukeInvalidTimeException() {
        super("FAILURE: Your command has to be in a proper format like " +
                "the one here <command> <activity> <by/at> <Year-Month-Day Hour:Minute>");
    }
}
