public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public TodoTask markAsDone() {
        return new TodoTask(description, true);
    }

    @Override
    public String printData() {
        return "T|" + super.printData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}