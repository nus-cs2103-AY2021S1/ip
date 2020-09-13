package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String findCondition;
    public FindCommand(String findCondition) {
        this.findCondition = findCondition;
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.getTasks(taskList.find(findCondition));
    }
}
