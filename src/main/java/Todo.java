public class Todo extends Task {
    public Todo(String title) {
        super(title);
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
}