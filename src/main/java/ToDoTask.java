public class ToDoTask extends Task {

    public ToDoTask(String summary) {
        super(summary);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
