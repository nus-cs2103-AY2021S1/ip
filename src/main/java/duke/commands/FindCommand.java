package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds tasks based on certain conditions.
 */
public class FindCommand extends Command {
    private String findCondition;

    /**
     * Creates a {@code FindComamnd} with given user input as find condition.
     *
     * @param findCondition A String of user input.
     */
    public FindCommand(String findCondition) {
        this.findCondition = findCondition;
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.getTasks(taskList.find(findCondition));
    }
}
