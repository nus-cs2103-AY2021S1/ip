package duke.command;

import java.util.List;

import duke.misc.Ui;
import duke.task.TaskList;


public class ClearCommand extends Command {
    public ClearCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) {
        taskList.clearAll();
        return Ui.clear();
    }
}
