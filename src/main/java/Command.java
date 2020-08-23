public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, DukeStorage storage);
    public abstract boolean isCompleted();
}
