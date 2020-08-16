public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Todo: " + super.getTask() + "\n";
    }
}
