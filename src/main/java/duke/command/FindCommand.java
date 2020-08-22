package duke.command;

import duke.*;
import duke.task.*;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> relatedTasks = taskList.findTasks(keyword);
        ui.printSearchResult(relatedTasks);
    }
}
