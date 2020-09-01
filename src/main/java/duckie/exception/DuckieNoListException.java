package duckie.exception;

/**
 * DuckieException thrown when the input command targets a Task but there are no
 * task in the List
 */
public class DuckieNoListException extends DuckieException {

    /**
     * Instantiate DuckieNoListException
     */
    public DuckieNoListException() {
        super("Quack. You have no tasks in the list currently.");
    }
}
