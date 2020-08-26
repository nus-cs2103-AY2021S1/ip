public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileString() {
        return "T|" + super.fileString();
    }
}
