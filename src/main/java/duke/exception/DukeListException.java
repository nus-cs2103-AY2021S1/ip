package duke.exception;

/**
 * DukeListException class for when the list of tasks in Duke behaves out of expectations.
 * @author Kor Ming Soon
 */
public class DukeListException extends DukeException {

    /**
     * Constructor for DukeListException
     * @param str Error message.
     */
    public DukeListException(String str) {
        super(str);
    }

}