package duke.exception;

public class DukeInvalidTaskTimeException extends DukeTaskException {

    @Override
    public String toString() {
        return "ERROR: Please specify a correct date/time for this task!\n"
                + "    Time formatting: dd-MM-yyyy HH:mm";
    }

}
