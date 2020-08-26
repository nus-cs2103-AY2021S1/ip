public class DuckieInvalidCommandException extends DuckieException {
    protected final static String INDENT = "\t";

    public DuckieInvalidCommandException() {
        super(INDENT + "Sorry, Duckie does not understand what you are trying to do.");
    }

}
