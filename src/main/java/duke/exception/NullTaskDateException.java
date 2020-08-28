package duke.exception;

public class NullTaskDateException extends DukeException {

    public NullTaskDateException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        String timeCommand;

        switch (command) {
            case "deadline":
                timeCommand = " using '/by [parsable date]'";
                break;
            case "event":
                timeCommand = " using '/at [parsable date]'";
                break;
            default:
                timeCommand = "";
        }

        return "Please provide time to the " + command + " item" + timeCommand + "!";
    }
}
