public class ToDo extends Task {

    private ToDo (String task, boolean isDone) {
        super(task, isDone);
    }

    public ToDo(String task) {
        super(task);
    }

    @Override
    public ToDo markDone() {
        return new ToDo(task, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
