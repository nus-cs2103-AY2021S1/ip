public class Deadline extends Task {
    protected String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    public Deadline(String title, String by, boolean isDone) {
        super(title);
        this.by = by;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        Task newTask = new Deadline(title, by);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        return String.format("%s[D] %s (by: %s)", status, title, by);
    }

    public String toData() {
        int status = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", status, title, by);
    }
}