public class Deadline extends Task {
    Deadline(String message) {
        super(message);
    }

    @Override
    public String getTypeLetter() {
        // dummy value
        return "[D]";
    }
}