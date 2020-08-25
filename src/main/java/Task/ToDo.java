package Task;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description);
        this.completed = isDone;
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        return "[T]" + icon + " " + this.description;
    }

    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "T>" + completedBinary + ">" + this.description;
    }
}
