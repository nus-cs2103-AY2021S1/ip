package duke.tasks;

public class Task {
    public String description;
    public boolean isDone;
    public int index;
    public final String done = "[\u2713] ";
    public final String start = "[\u2718] ";
    public TaskType type;

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }
    public Task(String description, int index, boolean isOver) {
        this.description = description;
        this.isDone = isOver;
        this.index = index;
    }

    public String getStatusWithIndex() {
        return String.format("%s. %s%s", index, isDone ? this.done : this.start, this.description);
//        return isDone ? index + ". " + this.done /*"[\u2713] "*/ + this.description
//                : index + ". " + this.start/*"[\u2718] "*/ + this.description; //return tick or X symbols
    }

    public String toString() {
        return String.format("%s%s", isDone ? this.done : this.start, this.description);
        //return (isDone ? this.done/*"[\u2713] "*/ + this.description : this.start/*"[\u2718] "*/ + this.description);
    }
}