package luke.commands;

import java.util.List;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.exception.LukeNoResultException;
import luke.task.Task;

public class FindCommand extends Command {

    protected String keyword;

    /**
     * Creates a FindCommand object to find tasks
     * that matches the given keyword.
     *
     * @param keyword the keyword used to find related tasks
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            List<Task> result = tasks.findTask(this.keyword);
            String resultStr = "Here are the tasks in your list.\n";
            for (int i = 0; i < result.size(); i++) {
                Task current = result.get(i);
                resultStr += String.format("%d.%s\n", i + 1, current);
            }
            return resultStr;
        } catch (LukeNoResultException e) {
            throw e;
        }

    }
}
