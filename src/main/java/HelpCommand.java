/**
 * Implements methods for HelpCommand.
 */
public class HelpCommand extends Command {
    /**
     * Checks if the program has to exit Duke.
     *
     * @return exitCheck as False
     */
    @Override
    public boolean exitChecker() {
        return false;
    }

    /**
     * Runs command to handle find command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return Response object
     */
    @Override
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        Response responseObject = ui.helpText();
        return responseObject;
    }
}

