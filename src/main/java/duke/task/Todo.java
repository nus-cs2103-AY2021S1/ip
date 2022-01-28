package duke.task;

public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    public Todo(String title, boolean isDone) {
        super(title);
        this.isDone = isDone;
    }

    public Task markAsDone() {
        Task newTask = new Todo(title);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        return status + "[T] " + title;
    }

    public String toData() {
        int status = isDone ? 1 : 0;
        return String.format("T | %d | %s", status, title);
    }
}