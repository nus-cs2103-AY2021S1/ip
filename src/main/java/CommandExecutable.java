@FunctionalInterface
public interface CommandExecutable {
    void run(TaskList taskList, String[] arguments) throws DukeException;
}
