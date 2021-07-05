package duke;

/**
 * Represents a command to delete tasks.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructs a command to delete tasks.
     * 
     * @param input String input of the task to be deleted.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        assert input.length() > 6 : "Please provide index of task";
        try {
            int indexOfTask = Integer.parseInt(input.substring(7)) - 1;
            Task task = taskList.get(indexOfTask);
            taskList.deleteTask(indexOfTask);
            store.writeFile(taskList);
            return ui.showDeletion(task, taskList);
        } catch (NumberFormatException e) {
            return "Please provide a valid index for the task to be deleted";
        } catch (IndexOutOfBoundsException e) {
            return "Task not found in list";
        }
    }
}
