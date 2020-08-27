// for deadline or exception
public class WrongDeadlineException extends DukeException {
    public WrongDeadlineException(String cmd, String separator) {
        super("You type wrong lah!" +
                "\nTry \"" +
                cmd +
                " {description of task} " +
                separator +
                " {date and time in this format: dd/MM/yyyy HH:mm}\"");
    }
}
