package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code: FindCommand} is a child of {@code: Command} object.
 *      On execution, it will search all task description and list those who matched the search criteria.
 */
public class FindCommand extends Command {
    private String search;

    /**
     * Constructs a FindCommand which search the keyword on execution.
     *
     * @param search Keyword to search.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Searches all the task's description and list the task with that keyword.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        List<Task> hits = new ArrayList<>();
        List<Integer> idxList = new ArrayList<>();

        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i-1).getDesc().contains(search)) {
                hits.add(tasks.get(i-1));
                idxList.add(i);
            }
        }

        ui.printSearchResult(search, idxList, hits);
    }
}
