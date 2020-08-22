import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.completed = "0";
    }

    public String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    public void markAsDone() {
        this.isDone = true;
        this.completed = "1";
    }

    public static Task textToTask(String text) {
        String[] task = text.split("\\|");
        Task existingTask = null;
        switch (task[0]) {
        case "T":
            existingTask = new ToDo(task[2]);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        case "D":
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime by = LocalDateTime.parse(task[3], deadlineFormatter);
            existingTask = new Deadline(task[2], by);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        case "E":
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime at = LocalDateTime.parse(task[3], eventFormatter);
            existingTask = new Event(task[2], at);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        }
        return existingTask;
    }

    public String taskToText() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
