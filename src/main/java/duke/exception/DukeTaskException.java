package duke.exception;

/**
 * DukeTaskException class for when Duke behaves out of expectations.
 * @author Kor Ming Soon
 */
public class DukeTaskException extends DukeException {

    /**
     * Constructor for DukeTaskException
     * @param str Error message.
     */
    public DukeTaskException(String str) {
        super(str);
    }

}
