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
            timeCommand = " using '/by YYYY-MM-DD'";
            break;
        case "event":
            timeCommand = " using '/at YYYY-MM-DD'";
            break;
        default:
            timeCommand = "";
        }

        return "Please provide time to the " + command + " item" + timeCommand + "!";
    }
}
