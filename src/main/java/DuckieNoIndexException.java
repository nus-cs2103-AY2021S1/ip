public class DuckieNoIndexException extends DuckieException {
    protected final static String INDENT = "\t";
    protected final static String horizL = INDENT +
            "____________________________________________________________";

    public DuckieNoIndexException() {
        super(horizL + "\n" + INDENT
                + "Quack. The input index is not found in the list.\n"
                + horizL);
    }
}
