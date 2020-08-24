package java1.tasklist;

public class Todos extends Task {

    public Todos(String description) {
        super(description);
    }

    public Todos(String description, boolean isDone) {
        super(description,isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
