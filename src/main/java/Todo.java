public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveTask() {
        return "T" + super.saveTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}