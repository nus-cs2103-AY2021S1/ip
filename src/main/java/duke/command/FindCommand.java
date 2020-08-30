package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        ui.printFindResult(getListQueryResult(tasks));
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        return ui.getFindResultAsString(getListQueryResult(tasks));
    }

    private TaskList getListQueryResult(TaskList tasks) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskName().contains(query)) {
                foundTasks.addTask(task);
            }
        }

        return foundTasks;
    }
    private void verifyInput() throws DukeException {
        if (query.isBlank()) {
            throw DukeException.badFind();
        }
    }
}
