public class ToDo extends Task {

    protected ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.isDone() ? "\u2713" : "\u2717",
                super.getTask());
    }
}
