package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public class StatsCommand extends Command {
    public StatsCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.displayStatistics();
    }
}
