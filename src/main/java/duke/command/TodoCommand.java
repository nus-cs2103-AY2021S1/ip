package duke.command;

import duke.Duke;
import duke.task.ToDo;

/**
 * TodoCommand creates a ToDo task, stores it in task list, reports to the user.
 */
public class TodoCommand extends Command {

    /**
     * Constructs a TodoCommand.
     */
    public TodoCommand() {
        names = new String[] { "todo" };
        description = "Creates a todo task.";
        format = CommandFormat.TODO_CMD_FORMAT;
    }

    /**
     * Creates a ToDo task, stores it in task list, reports to the user.
     * @param str the task info
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        ToDo newToDo = ToDo.createToDo(str);
        duke.getTaskList().addTask(newToDo);
        response(newToDo, duke);
    }

    private void response(ToDo newToDo, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportNewTask(newToDo);
        } else {
            duke.getUiResponse().reportNewTask(newToDo);
        }
    }
}
