public class DoneCommand extends Command {
    int taskIndex;
    boolean exitCheck;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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

    public boolean exitCheck() {
        exitCheck = false;
        return exitCheck;
    }
}