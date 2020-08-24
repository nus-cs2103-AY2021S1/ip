package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {
    
    private final int argument;

    public DoneCommand(int argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        ui.printToConsole(taskList.markTaskAsDone(argument, storage));
    }
}
