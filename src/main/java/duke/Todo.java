package duke;
public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDisk() {
        return String.format("todo\n%s\n%d", desc, (done ? 1 : 0));
    }
}