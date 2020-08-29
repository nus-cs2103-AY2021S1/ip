package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find certain task with keyword.
 */
public class FindCommand implements Command {

    /** Search keyword for the list */
    private final String keyword;
    private final TaskList resultTaskList = new TaskList();

    /**
     * Creates a find command with search keyword.
     * @param key Search keyword.
     */
    public FindCommand(String key) {
        this.keyword = key;
    }


    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {

        //Add the tasks whose description contains keyword
        for (Task task: tasks.getTasks()) {
            if (task.containsKeyWord(keyword)) {
                resultTaskList.add(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return Ui.getTaskListString(resultTaskList);
    }

}
