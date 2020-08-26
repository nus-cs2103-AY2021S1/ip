public class DuckieInsufficientInfoException extends DuckieException {
    protected final static String INDENT = "\t";

    public DuckieInsufficientInfoException() {
        super(INDENT + "Duckie needs more description after the first word.");
    }
}
