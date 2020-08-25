package duke.exception;

/**
 * DukeCommandException class for when the commands or inputs in Duke behaves out of expectations.
 * @author Kor Ming Soon
 */
public class DukeCommandException extends DukeException {

    /**
     * Constructor for DukeCommandException
     * @param str Error message.
     */
    public DukeCommandException(String str) {
        super(str);
    }

}
