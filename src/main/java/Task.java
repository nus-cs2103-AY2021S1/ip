import java.time.LocalDate;

public class Task {

    public String description;
    public boolean isDone;
    public String type;
    public LocalDate time;

    public Task(String task, boolean isDone, LocalDate time) {

        description = task;
        this.isDone = isDone;
        type = null;
        this.time = time;

    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void addTask(String task) {
        System.out.println("added : " + task);
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "]" +
                " " + this.getDescription();
    }
}
