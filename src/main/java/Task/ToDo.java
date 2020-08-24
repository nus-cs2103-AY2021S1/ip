package Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        return "[T]" + icon + " " + this.description;
    }
}
