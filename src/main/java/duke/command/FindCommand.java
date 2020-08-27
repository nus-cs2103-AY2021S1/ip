package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> foundTasks = tasks.findTasks(keyword);

        if (foundTasks.size() == 0) {
            throw new DukeException("There are no tasks in your list with the keyword \""
                    + keyword + "\".");
        }

        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");

        for (Task task : foundTasks) {
            output.append(tasks.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        ui.showPrompt(output.toString());
    }
}
