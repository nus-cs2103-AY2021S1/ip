public class DeleteCommand extends Command {
    protected boolean isExit;
    protected int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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

    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}