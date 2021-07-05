package duke;

/**
 * Represents a command to add a todo task.
 */
public class ToDoCommand extends Command {
    private String input;

    /**
     * Constructs a todo command to add a todo task.
     * 
     * @param input String input of the todo task.
     */
    public ToDoCommand(String input) {
        this.input = input;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        assert input.length() > 4 : "Please use the format: todo <DESCRIPTION>";
        Task newTask;
        String taskDescription = input.substring(5);
        newTask = new ToDo(taskDescription);
        taskList.addTask(newTask);
        store.writeFile(taskList);
        return ui.showAddition(newTask, taskList);
    }
}
