package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {
    private TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("OOPS!!! There is no task in the list.");
        }

        StringBuilder concat = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            concat.append(String.format("\n\t%d. %s", i + 1, tasks.get(i)));
        }

        return "Here are the tasks in your list: " + concat;
    }
}
