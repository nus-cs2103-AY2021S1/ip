package duke;

public class TodoStub extends Task {

    public TodoStub(String description) {
        super(description);
    }

    @Override
    public String toString() {
        // String status = done ? "[✓]" : "[✗]";
        return "[T]" + "[✗]" + " " + description;

    }
}
