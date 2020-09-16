package duke;

/**
 * Represents a command to complete tasks.
 */
public class CompleteCommand extends Command {
    private int number;

    /**
     * Constructs a command to complete a task.
     * 
     * @param number index of the task to be completed.
     */
    public CompleteCommand(int number) {
        this.number = number;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        Task task = taskList.get(number);
        task.completeTask();
        store.write(taskList);
        return ui.showCompletion(task);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
