package duke.command;

import duke.task.Task;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;


    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        List<Task> temp = tasks.findKeyword(keyword);
        if (temp.size() <= 0) {
            throw new DukeException("No tasks found, please refine your search");
        }
        ui.displayList(temp, "Here are the matching tasks in your list: ");

    }
}