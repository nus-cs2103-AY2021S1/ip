package duke.exception;

/**
 * DukeIndexException class for when the index of tasks in Duke behaves out of expectations.
 * @author Kor Ming Soon
 */
public class DukeIndexException extends DukeException {

    /**
     * Constructor for DukeIndexException
     * @param str Error message.
     */
    public DukeIndexException(String str) {
        super(str);
    }

}