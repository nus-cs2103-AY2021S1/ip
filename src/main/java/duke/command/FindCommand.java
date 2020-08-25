package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (query.isBlank()) {
            throw DukeException.badFind();
        }

        TaskList foundTasks = new TaskList();

        for (Task task : tasks) {
            if (task.getTaskName().contains(query)) {
                foundTasks.addTask(task);
            }
        }

        ui.printFindResult(foundTasks);
    }
}
