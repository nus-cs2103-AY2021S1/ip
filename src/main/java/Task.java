public class Task {
    protected String title;
    protected boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task markAsDone() {
        Task newTask = new Task(title);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        return status + " " + title;
    }
}