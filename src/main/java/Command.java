abstract class Command {

    protected final String command;

    public Command(String command){
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NoDateException, InvalidCommandException, NoTaskException;
    public abstract boolean isExit();
}
