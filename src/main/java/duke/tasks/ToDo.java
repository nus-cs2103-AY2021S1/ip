package duke.tasks;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    public ToDo(boolean isDone, String name) { super(isDone, name); }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
