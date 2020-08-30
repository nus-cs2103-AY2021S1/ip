package duke.command;

import java.util.List;

import duke.misc.Ui;
import duke.task.TaskList;


public class ListCommand extends Command {
    public ListCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) {
        return Ui.list(taskList.printTasks());
    }
}
