package stub;

import duke.exception.DukeException;

/**
 * Represents a DukeException in the program
 * Used for testing
 */
public class DukeExceptionStub extends DukeException {
    public DukeExceptionStub() {
        super("TESTING", DukeExceptionStub.class.getName());
    }

    @Override
    public String guiString() {
        return "TESTING";
    }
}
