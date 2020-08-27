public interface Command {
    public void execute(TaskList tasks, Ui ui);
    public boolean isExit();
}
