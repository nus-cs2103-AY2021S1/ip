public class Deadline extends Task {

    Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
