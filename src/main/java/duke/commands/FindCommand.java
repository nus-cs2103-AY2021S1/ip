package duke.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        TaskList keywordTasks = new TaskList();
        for(int i = 0; i < taskList.numTask(); i++) {
            Task nextTask = taskList.get(i);
            if (nextTask.toString().contains(keyword)) {
                keywordTasks.add(nextTask);
            }
        }
        ui.printFoundItems(keywordTasks, this.keyword);
    }
}
