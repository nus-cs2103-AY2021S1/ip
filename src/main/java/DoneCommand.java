public class DoneCommand extends Command {
    protected int taskIndex;
    protected boolean isExit;

    /**
     * Instantiates DoneCommand object.
     * @param taskIndex Index of task to be deleted.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Runs command to handle done command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return void
     */
    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        try {
            int arraySize = arrayOfTasks.taskArraySize();
            if (taskIndex < 0 || taskIndex >= arraySize) {
                throw new DukeException("The task number" + " (" + (taskIndex + 1) + ") " +
                                        "that you have input can not be found in your list.");
            } else {
                // Do nothing.
            }
            arrayOfTasks.get(taskIndex).setDone();
            Task doneTask = arrayOfTasks.get(taskIndex);
            ui.doneMessage(doneTask);
            storage.changeFile();
        } catch (DukeException error) {
            System.out.println(error);
        }
    }

    /**
     * Since this is not a exit command, it does not signal the program to exit.
     *
     * @return exitCheck as False
     */
    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}