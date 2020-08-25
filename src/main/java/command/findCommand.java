package command;

import exceptions.DukeException;
import task.TaskList;

public class findCommand extends Command {
    String keyword;

    public findCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    @Override
    public void execute() throws DukeException {
        this.tasks.findTask(keyword);
    }
}
