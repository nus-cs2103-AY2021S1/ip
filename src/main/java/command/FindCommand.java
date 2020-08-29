package command;

import exceptions.DukeException;
import task.TaskList;

public class FindCommand extends Command {
    protected String keyword;

    /**
     * Creates FindCommand object to execute finding tasks with given keyword.
     * @param tasks TaskLists with Tasks to search through.
     * @param keyword String to search Tasks that contains keyword.
     */
    public FindCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    @Override
    public void execute() throws DukeException {
        this.tasks.findTask(keyword);
    }
}
