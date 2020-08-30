package duke.exception;

/** An exception when there is no index given following the DoneCommand or DeleteCommand. */
public class NoIndexException extends DukeException {

    /**
     * Constructs a NoIndexException.
     *
     * @param deleteOrDone The type of command: DeleteCommand or DoneCommand.
     */
    public NoIndexException(String deleteOrDone) {
        super("Invalid format. After \"" + deleteOrDone + "\", you need to put "
            + "at least one positive integer separated by [space]");
    }
}
