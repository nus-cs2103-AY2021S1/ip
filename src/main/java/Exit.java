public class Exit extends Task {
    static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    static final String TYPE = "bye";

    Exit() {
        super(EXIT_MESSAGE, TYPE);
    }

    @Override
    public String toString() {
        return EXIT_MESSAGE;
    }
}
