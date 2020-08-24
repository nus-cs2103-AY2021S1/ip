public class InvalidEventFormatException extends DukeException{
    public InvalidEventFormatException() {
        super("☹ OOPS!!! The format of event command seems to be wrong.\n"
                + "Try the following format: event task /at YYYY-MM-DD HH:MM");
    }
}
