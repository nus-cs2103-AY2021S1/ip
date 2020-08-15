package Duke;

public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
