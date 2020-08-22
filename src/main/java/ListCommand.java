public class ListCommand extends Command {
    boolean exitCheck;

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

    public boolean exitCheck() {
        exitCheck = false;
        return exitCheck;
    }
}