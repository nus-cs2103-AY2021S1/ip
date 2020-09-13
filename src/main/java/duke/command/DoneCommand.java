package duke.command;

import java.io.IOException;

import duke.error.*;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    int index;
    public DoneCommand(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IncorrectFormat, IOException, EventDateParseException, DeadlineDateParseException, DeleteListEmptyException, DeleteOutOfBounds, DeleteNegativeIndex {
        Task done = tasks.markDone(index, storage);
        return ui.printDone(done);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
