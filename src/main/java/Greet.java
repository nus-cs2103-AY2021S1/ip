public class Greet extends Task {
    static final String MESSAGE = "Hello! I'm Duke\n"
        + "What can I do for you?\n";
    static final String TYPE = "greet";

    Greet() {
        super(MESSAGE, TYPE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
