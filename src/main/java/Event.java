public class Event extends Task {
    protected String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    public Task markAsDone() {
        Task newTask = new Event(title, at);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[v]" : "[x]";
        return String.format("%s[E] %s (at: %s)", status, title, at);
    }
}