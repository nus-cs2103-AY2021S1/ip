public abstract class Command {
    String fullCommand;
    boolean isExit;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
