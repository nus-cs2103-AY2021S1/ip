package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class DateCommand extends Command {
    
    private final String argument;

    public DateCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {
        ui.printToConsole(taskList.taskListToDateFilteredString(argument));
    }
}
