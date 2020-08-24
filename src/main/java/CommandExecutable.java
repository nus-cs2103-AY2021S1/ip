@FunctionalInterface
public interface CommandExecutable {
    void run(TaskList taskList, Ui ui, String[] arguments) throws DukeException;
}
