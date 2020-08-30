package duke.command;

import java.util.List;

import duke.misc.Ui;
import duke.task.TaskList;


public class FindCommand extends Command {
    public FindCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) {
        return Ui.find(taskList.findTasks(input.get(1)));
    }
}
