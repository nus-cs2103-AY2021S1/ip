package duke.commands;


import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists out all of tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.getTasks(taskList);
    }
}
