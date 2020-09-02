package duke.command;

import duke.Duke;
import duke.exception.NoDescriptionException;
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
    }

    /**
     * Creates a ToDo task, stores it in task list, reports to the user.
     * @param str the task info
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        ToDo newToDo = ToDo.createToDo(str);
        duke.taskList.addTask(newToDo);
        duke.ui.reportNewTask(newToDo);
    }


}
