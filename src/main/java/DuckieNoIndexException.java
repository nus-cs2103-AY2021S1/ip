public class DuckieNoIndexException extends DuckieException {
    protected final static String INDENT = "\t";

    public DuckieNoIndexException() {
        super(INDENT + "Quack. The input index is not found in the list.");
    }
}
