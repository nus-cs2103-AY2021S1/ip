package executor;

import command.Command;
import dukeoutput.DukeOutput;
import tasklist.TaskList;

public class ListCommandExecutor extends CommandExecutor {

    @Override
    public void execute(Command command, TaskList taskList) {
        DukeOutput.output(taskList.getListInfo());
    }
}
