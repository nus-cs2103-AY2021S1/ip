package data.task;

//  ToDos: data.tasks without any date/time attached to it
//  e.g., visit new theme park
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String fileFormat() {
        return String.format("%1$s/%2$s/%3$s", "T", super.getStatusIcon(), super.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%1$s", super.toString());
    }
}