public class Deadline extends Task{

    private final String task;
    private final String deadline;
    private static final String errorMessage = "OOPS!!! The description of a deadline cannot be empty.\n";

    protected Deadline(String command) throws DukeException{
        if (command.equals("deadline")) {
            throw new DukeException(errorMessage);
        } else {
            int index = command.indexOf("/by");
            this.task = command.substring(9, index - 1);
            this.deadline = command.substring(index + 4);
        }
    }

    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                getTask(),
                deadline);
    }

}
