package duke.stub.command;

import duke.command.CommandExecutor;
import duke.exception.DukeException;
import duke.task.TaskList;

public class CommandExecutorStub implements CommandExecutor {
    @Override
    public String execute(String in, TaskList taskList) {
        return "";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public void loadSaveString(String in, TaskList taskList) throws DukeException {

    }
}
