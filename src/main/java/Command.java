/**
 * The Command is the response of the program to be executed.
 * Execution of command may involve manipulation of TaskList,
 * Ui, or Storage depending on the required response.
 */
public interface Command {
    boolean isExit();
    void execute(TaskList tasks, Ui ui, Storage storage);
}
