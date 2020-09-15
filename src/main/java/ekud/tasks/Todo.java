package ekud.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public Todo copy() {
        Todo ret = new Todo(description);
        ret.isDone = isDone;

        return ret;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
