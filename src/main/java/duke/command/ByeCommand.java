package duke.command;

import java.util.List;

import duke.misc.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) {
        taskList.save();
        return Ui.bye();
    }
}
