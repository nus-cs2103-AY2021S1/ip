public class ToDo extends Task {

    private final String task;
    private static final String errorMessage = "OOPS!!! The description of a todo cannot be empty.\n";

    protected ToDo(String task) throws DukeException{
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
