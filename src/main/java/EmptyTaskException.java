public class EmptyTaskException extends DukeException {
    private static String emptyTaskMsg1 = "The description of a ";
    private static String emptyTaskMsg2 = " cannot be empty.";
    public EmptyTaskException(String command) {
        super(emptyTaskMsg1 + command + emptyTaskMsg2);
    }
}
