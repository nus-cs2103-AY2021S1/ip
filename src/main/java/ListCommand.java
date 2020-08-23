public class ListCommand extends Command {
    protected boolean isExit;

    /**
     * Runs command to handle list command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return void
     */
    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        try {
            int arraySize = arrayOfTasks.taskArraySize();
            if (arraySize == 0) {
                throw new DukeException("There is nothing to list as the list is currently empty.");
            } else {
                System.out.println("These are the tasks in your list:");
                ui.listTasks(arrayOfTasks);
            }
        } catch (DukeException error) {
            System.err.println(error);
        }
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return exitCheck as False
     */
    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}