package luke.exception;

/**
 * Represents an exception thrown when there is no task that matches the keyword.
 */
public class LukeNoResultException extends LukeException {
    /**
     * Creates a LukeNoResultException object that indicates no search results.
     *
     * @param keyword keyword used to search tasks
     */
    public LukeNoResultException(String keyword) {
        super(String.format("Sorry, there are no tasks that contains the keyword '%s.'"
                + "Please try again with another keyword.", keyword));
    }
}
