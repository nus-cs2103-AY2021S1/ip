public class Deadline extends Task {
    protected String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    public Task markAsDone() {
        Task newTask = new Deadline(title, by);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[v]" : "[x]";
        return String.format("%s[D] %s (by: %s)", status, title, by);
    }
}