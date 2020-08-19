package duke;

public class Deadline extends Task {

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
