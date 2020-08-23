public interface Command {
    void execute(TaskList tasks, Ui ui) throws Exception;
    boolean isExit();
}
