public abstract class Command {

    protected final String[] parsedCommand;

    public Command(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
