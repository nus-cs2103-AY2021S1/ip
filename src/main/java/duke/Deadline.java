package duke;

public class Deadline extends Task {

    Deadline(String name, String deadline) {
        super(name, deadline);
    }

    @Override
    String getIndicator() {
        return "[D]";
    }
}
