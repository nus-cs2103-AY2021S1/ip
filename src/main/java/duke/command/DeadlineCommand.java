package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;

public class DeadlineCommand extends TaskCommand {
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, EmptyDateException {
        Deadline deadline = new Deadline(fullCommand);
        tasks.add(deadline);
        storage.save(tasks);
        return addedTaskMessage(deadline, tasks);
    }
}
