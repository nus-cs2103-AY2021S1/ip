package duke.stub.command;

import duke.command.CommandExecutor;
import duke.task.TaskList;
import duke.util.Storage;

import java.util.ArrayList;

public class CommandExecutorStub implements CommandExecutor {
    private final ArrayList<String> HISTORY = new ArrayList<>();

    @Override
    public String execute(String in, TaskList taskList, Storage storage) {
        HISTORY.add(in);
        return "";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    public String getHistory() {
        return String.join("", HISTORY);
    }
}
