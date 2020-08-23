package Task;

import Task.Task;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description);
        this.completed = isDone;
    }

    public String toString() {
        String icon;
        if (this.completed) {
            icon = "[" + "\u2713" + "]";
        } else {
            icon = "[" + "\u2718" + "]";
        }
        return "[T] " + icon + " " + this.description;
    }

    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "T|" + completedBinary + "|" + this.description;
    }
}
