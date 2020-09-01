public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui);

    public abstract boolean isBye();
}
