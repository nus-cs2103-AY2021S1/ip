public abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IncorrectFormat;

    abstract boolean isExit();
}
