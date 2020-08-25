/**
 * Represents the command to close Duke
 */
public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to save the files for closing.
     * @param tasks List of tasks that Duke is handling.
     * @param ui Handles what the user reads.
     * @param storage Writes the save file.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidSaveFileException {
        ui.printOutput("\tBye. Hope to see you again soon!");
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Lets the main logic know to exit the loop
     * @return true to exiting the loop
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
