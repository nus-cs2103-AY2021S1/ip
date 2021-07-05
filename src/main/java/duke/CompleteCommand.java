package duke;

/**
 * Represents a command to complete tasks.
 */
public class CompleteCommand extends Command {
    private String input;

    /**
     * Constructs a command to complete a task.
     * 
     * @param input String input of the task to be completed.
     */
    public CompleteCommand(String input) {
        this.input = input;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        assert input.length() > 4 : "Please use the format: done <INDEX>";
        try {
            int indexOfTask = Integer.parseInt(input.substring(5)) - 1;
            Task task = taskList.get(indexOfTask);
            task.completeTask();
            store.writeFile(taskList);
            return ui.showCompletion(task);
        } catch (NumberFormatException e) {
            return "Please provide a valid index for the task to be completed";
        } catch (IndexOutOfBoundsException e) {
            return "Task not found in list";
        }
    }
}
