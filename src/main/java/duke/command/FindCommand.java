package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand finds all tasks matching keyword
 */
public class FindCommand extends Command {
    public FindCommand(String commandString) {
        super(CommandType.FIND, commandString);
    }

    /**
     * Finds all the tasks in the task list that matches keyword
     *
     * @param tasks task list of tasks
     * @throws DukeException when the command string has no keyword
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        String keyword = super.getTaskDescription();
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        Ui.showList(filteredTasks);
    }
}
