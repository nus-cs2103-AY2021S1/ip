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

    /**
     * Finds task with given keyword in list of tasks.
     * @return String to inform user task of tasks that match keyword.
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        return this.tasks.findTask(keyword);
    }
}
