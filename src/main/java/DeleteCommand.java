public class DeleteCommand extends Command {
    protected boolean isExit;
    protected int taskIndex;

    /**
     * Instantiates DeleteCommand object.
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Runs command to handle delete command.
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
                throw new DukeException("The task number" + " (" + (taskIndex + 1) + ") " + "that you want to " +
                                        "delete can not be found in your list.");
            } else {
                Task taskToDelete = arrayOfTasks.get(taskIndex);
                ui.removeMessage(taskToDelete);
                arrayOfTasks.deleteTask(taskIndex);
                ui.printTaskCount();
                storage.changeFile();
            }
        } catch (DukeException error) {
            System.out.println(error);
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