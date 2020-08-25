package duke;

/**
 * Encapsulates an Exit Command.
 */
public class ExitCommand extends Command {
    public ExitCommand(String[] parsedCommand) {
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
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        System.out.println("Sayonara! See you again my friend!");
    }

    /**
     * Determines if it is the end of a program.
     * @return false to indicate end of program
     */
    @Override
    boolean isExitProgram() {
        return true;
    }
}
