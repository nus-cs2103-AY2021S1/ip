package duke;

/**
 * Encapsulates a Clear Command.
 */
public class ClearCommand extends Command {
    /**
     * Instantiates a Clear Command
     * @param parsedCommand the parsed command
     */
    public ClearCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes command and write to storage.
     * @param tasks the tasklist containing tasks so far
     * @param ui ui to interact with user
     * @param storage storage to read and write to storage file
     * @throws DukeException if parsedCommand does not meet the requirements
     */
    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clearList();
        System.out.println("All your tasks have been removed!\n");
    }
}
