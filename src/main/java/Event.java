public class Event extends Task {
    protected String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    public Event(String title, String at, boolean isDone) {
        super(title);
        this.at = at;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        Task newTask = new Event(title, at);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        return String.format("%s[E] %s (at: %s)", status, title, at);
    }

    public String toData() {
        int status = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", status, title, at);
    }
}