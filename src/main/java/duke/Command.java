package duke;

public abstract class Command {
    String[] parsedCommand;

    public Command(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    abstract void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExitProgram() {
        return false;
    }
}
