package duke;

/**
 * Represents a command to delete tasks.
 */
public class DeleteCommand extends Command {
    private int number;

    /**
     * Constructs a command to delete tasks.
     * 
     * @param number Index of the task to be deleted.
     */
    public DeleteCommand(int number) {
        this.number = number;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        try {
            Task task = taskList.get(number);
            taskList.deleteTask(number);
            store.writeFile(taskList);
            return ui.showDeletion(task, taskList);
        } catch (NumberFormatException e) {
            return "Please provide a valid index for the task to be deleted";
        } catch (IndexOutOfBoundsException e) {
            return "Task not found in list";
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
