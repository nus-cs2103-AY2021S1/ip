/**
 * Represents a toDo command.
 */
public class ToDoCommand extends Command {
    private String afterCommand;

    /**
     * Constructor for the todo command.
     * @param afterCommand details of todo command.
     */
    public ToDoCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs an after command
        if (afterCommand == null) {
            return ui.throwDukeException(new DukeException(
                    "Please do not leave the todo description empty!"));
        } else {
            Task newToDo = new ToDo(afterCommand);
            taskList.addTask(newToDo);
            return ui.addTask(newToDo, taskList.getTasksSize(), false);
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
