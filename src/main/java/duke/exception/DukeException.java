package duke.exception;

/**
 * DukeException class for when the index of tasks in Duke behaves out of expectations.
 * @author Kor Ming Soon
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     * @param str Error message.
     */
    public DukeException(String str) {
        super(str);
    }

}
