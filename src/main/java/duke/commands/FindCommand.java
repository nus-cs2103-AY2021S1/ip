package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.TaskList;

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
