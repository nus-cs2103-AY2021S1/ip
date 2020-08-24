public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage);
}