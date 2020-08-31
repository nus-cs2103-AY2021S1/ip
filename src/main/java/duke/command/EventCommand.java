package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.Event;

public class EventCommand extends TaskCommand {
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, EmptyDateException {
        Event event = new Event(fullCommand);
        tasks.add(event);
        storage.save(tasks);
        System.out.println(addedTaskMessage(event, tasks));
    }
}
