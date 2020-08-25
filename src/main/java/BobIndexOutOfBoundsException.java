package main.java;

/**
 * An exception to be thrown when trying to access an index which does not exist.
 */
public class BobIndexOutOfBoundsException extends BobException {

    /**
     * Returns a message that indicates the index on the TaskList does not exist.
     *
     * @return a message that indicates the index on the TaskList does not exist.
     */
    @Override
    public String getMessage() {
        return "There are no tasks on the list with the provided index. Please check the list and try again!";
    }
}
