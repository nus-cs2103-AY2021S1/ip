public class Deadline extends Task {
    private String dueDate;
    Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String missingNameError() {
        return "The description of a deadline task cannot be empty.";
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        String eventTime = this.dueDate.length() > 0 ? " (by: " + this.dueDate + ")" : "";
        return "[D]" + marked + this.name + eventTime;
    }
}
