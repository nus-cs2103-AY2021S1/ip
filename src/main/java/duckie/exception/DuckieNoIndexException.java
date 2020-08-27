package duckie.exception;

/**
 * DuckieException thrown when the input index is not found in the TaskList
 */
public class DuckieNoIndexException extends DuckieException {
    protected final static String INDENT = "\t";

    /**
     * Instantiate DuckieNoIndexException
     */
    public DuckieNoIndexException() {
        super(INDENT + "Quack. The input index is not found in the list.");
    }
}
