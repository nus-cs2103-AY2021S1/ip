public interface Command {
    boolean isExit();
    void execute(TaskList tasks, Ui ui, Storage storage);
}
