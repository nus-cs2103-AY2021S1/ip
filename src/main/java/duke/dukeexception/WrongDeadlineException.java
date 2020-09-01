package duke.dukeexception;

// for deadline or exception
public class WrongDeadlineException extends DukeException {
    public WrongDeadlineException(String commandName, String separator) {
        super("You type wrong lah!" +
            "\nTry \"" +
            commandName +
            " {description of task} " +
            separator +
            " {date and time in this format: dd/MM/yyyy HH:mm}\"");
    }
}
