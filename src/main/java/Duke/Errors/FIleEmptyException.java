package Duke.Errors;

import Duke.Errors.DukeException;

/**
 * This exception is thrown when the file is empty
 */
public class FIleEmptyException extends DukeException {
    /**
     * this overrides the toString() method
     * @return a String representation of FileEmptyExcleption
     */
    @Override
    public String toString() {
        return "Task file is empty!";
    }
}
