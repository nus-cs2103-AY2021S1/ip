package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class FindCommand extends Command {
    private final String keyword;


    public FindCommand(String keyword) {
        this.keyword = keyword;
        aliases = new ArrayList<>();
        aliases.add("f");
        aliases.add("find");

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
