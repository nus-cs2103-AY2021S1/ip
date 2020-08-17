public class ToDo extends Task {

    private final String task;
    private static final String errorMessage = "OOPS!!! The description of a todo cannot be empty.\n";

    protected ToDo(String command) throws DukeException{
        if (command.equals("todo")) {
            throw new DukeException(errorMessage);
        } else {
            this.task = command.substring(5);
        }
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
