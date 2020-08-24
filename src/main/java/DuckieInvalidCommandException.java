public class DuckieInvalidCommandException extends DuckieException {
    protected final static String INDENT = "    ";
    protected final static String horizL = INDENT +
            "____________________________________________________________";

    public DuckieInvalidCommandException() {
        super(horizL + "\n" + INDENT
                + "Sorry, Duckie does not know what you are trying to do.\n"
                + horizL);
    }

}
