package duke.stub.command;

import duke.command.CommandExecutor;
import duke.exception.DukeException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.TaskList;

public class CommandExecutorStub implements CommandExecutor {
    @Override
    public Response execute(String in, TaskList taskList) {
        return new NormalResponse("");
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public void loadSaveString(String in, TaskList taskList) throws DukeException {

    }
}
