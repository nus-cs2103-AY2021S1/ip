import java.util.Map;

abstract class Command {

    // Abstract Methods

    /**
     * Executes the command.
     * @param tasks TaskList representing list of current tasks.
     * @params notes NotesList representing list of current notes.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer.
     */
    abstract String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables)
            throws DukeException;

    abstract boolean isExit();

}
