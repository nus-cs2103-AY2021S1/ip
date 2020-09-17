/**
 * Encapsulates the Command object, supports the function execute which executes a series of instructions.
 */
public abstract class Command {
    
    abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    
}
