public class Event extends Task{

    private final String task;
    private final String time;
    private static final String errorMessage = "OOPS!!! The description of a event cannot be empty.\n";

    protected Event(String command) throws DukeException {
        if (command.equals("event")) {
            throw new DukeException(errorMessage);
        } else {
            int index = command.indexOf("/at");
            this.task = command.substring(6, index - 1);
            this.time = command.substring(index + 4);
        }
    }

    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                getTask(),
                time);
    }

}
