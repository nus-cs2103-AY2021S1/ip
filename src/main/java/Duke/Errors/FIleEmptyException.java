package Duke.Errors;

/**
 * This exception is thrown when the file is empty
 */
public class FIleEmptyException extends DukeException {
    /**
     * this overrides the toString() method
     *
     * @return a String representation of FileEmptyException
     */
    @Override
    public String toString() {
        return "Task file is empty!";
    }
}
