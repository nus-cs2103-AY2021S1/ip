package duke.command;

import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command, listing all tasks containing the given keyword.
     *
     * @param taskList A TaskList instance.
     * @param ui A Ui instance.
     * @param storage A Storage instance.
     * @throws DukeException if there are no tasks containing the given keyword.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> foundTasks = taskList.findTasks(this.keyword);

        if (foundTasks.size() == 0) {
            throw new DukeException("There are no tasks in your list with the keyword \""
                    + this.keyword + "\".");
        }

        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");

        for (Task task : foundTasks) {
            output.append(taskList.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        ui.showPrompt(output.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            return this.keyword.equals(((FindCommand) obj).keyword);
        }

        return false;
    }
}
