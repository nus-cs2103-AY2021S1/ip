public class DuckieInsufficientInfoException extends DuckieException {
    protected final static String INDENT = "\t";
    protected final static String horizL = INDENT +
            "____________________________________________________________";

    public DuckieInsufficientInfoException() {
        super(horizL + "\n" + INDENT
                + "Duckie needs more description after the first word.\n"
                + horizL);
    }
}
