package duke.command;

import duke.Duke;
import duke.data.DukeTaskList;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand() {
        names = new String[] { "find" };
    }

    @Override
    public void execute(String str, Duke duke) {
        ArrayList<Task> tasksFound = duke.taskList.findTasks(str);
        duke.ui.reportGiveTasks(tasksFound);
    }
}
