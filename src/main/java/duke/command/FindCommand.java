package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find certain task with keyword.
 */
public class FindCommand implements Command{

    private final String keyword;

    public FindCommand(String key) {
        this.keyword = key;
    }

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList resultTaskList = new TaskList();

        //Add the tasks whose description contains keyword
        for(Task task: tasks.getTasks()) {
            if (task.containsKeyWord(keyword)) {
                resultTaskList.add(task);
            }
        }

        //Produce out put from result task list
        ui.showTaskList(resultTaskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
