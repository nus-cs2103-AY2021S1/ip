package exception;

/**
 * Thrown to notify user that they forgot to specify
 * index number of the tasks list when they run
 * DoneCommand or DeleteCommand.
 */
public class NoIndexException extends DukeException {

    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!! You have ton specify
     * which number in your lists.
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return super.toString() + " You have to specify which number in your lists";
    }
}
