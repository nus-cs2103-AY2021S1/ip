public class ToDo extends Task {

    private final String task;

    protected ToDo(String task) {
        this.task = task;
    }

    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.isDone() ? "\u2713" : "\u2717",
                getTask());
    }
}
