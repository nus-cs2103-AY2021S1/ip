package duckie.exception;

public class DuckieNoListException extends DuckieException {
    protected final static String INDENT = "\t";

    public DuckieNoListException() {
        super(INDENT + "Quack. You have no tasks in the list currently.");
    }
}
