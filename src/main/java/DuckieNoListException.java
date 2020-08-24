public class DuckieNoListException extends DuckieException {
    protected final static String INDENT = "\t";
    protected final static String horizL = INDENT +
            "____________________________________________________________";

    public DuckieNoListException() {
        super(horizL + "\n" + INDENT
                + "Quack. You have no tasks in the list currently.\n"
                + horizL);
    }
}
