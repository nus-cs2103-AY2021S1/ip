public abstract class Command {

    public abstract void process(TaskList tasks, Ui ui, Storage storage) throws AliceException;

    public boolean isExitCommand() {
        return false;
    }
}
