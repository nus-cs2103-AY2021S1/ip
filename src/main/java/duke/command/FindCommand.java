package duke.command;

import java.util.List;

import duke.task.Task;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class FindCommand extends Command {
    protected static List<String> aliases;
    private final String keyword;


    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        List<Task> temp = tasks.tasksContainingKeywords(keyword);
        if (temp.size() <= 0) {
            throw new DukeException("No tasks found, please refine your search");
        }
        ui.displayList(temp, "Here are the matching tasks in your list: ");

    }
}
