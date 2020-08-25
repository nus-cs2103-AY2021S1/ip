public abstract class Command {

    String input;
    boolean isExit;

    public abstract void execute(TaskManager taskManager, Ui ui);
}
