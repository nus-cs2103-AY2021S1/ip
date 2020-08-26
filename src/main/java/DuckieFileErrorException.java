public class DuckieFileErrorException extends DuckieException {
    protected final static String INDENT = "\t";

    public DuckieFileErrorException() {
        super(INDENT + "Duckie is facing some problems loading your file.");
    }
}
