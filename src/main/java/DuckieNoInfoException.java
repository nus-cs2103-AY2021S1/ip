public class DuckieNoInfoException extends DuckieException {
    protected final static String INDENT = "    ";
    protected final static String horizL = INDENT +
            "____________________________________________________________";

    public DuckieNoInfoException() {
        super(horizL + "\n" + INDENT
                + "Duckie needs more description after the first word.\n"
                + horizL);
    }
}
