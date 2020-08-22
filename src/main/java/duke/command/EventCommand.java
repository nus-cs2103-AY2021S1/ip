package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Event;

public class EventCommand extends ComplexCommand {

    public EventCommand(String params) {
        super(params);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {
        try {
            String[] splitParams = this.parseParams();
            Event newEvent = new Event(splitParams[0], splitParams[1]);
            taskManager.storeTask(newEvent);
        } catch (DukeInputException e) {
            ui.displayException(e);
        }
    }

    public String[] parseParams() throws DukeInputException {
        if (this.params.equals("")) {
            throw new DukeInputException("'event' requires parameters.\n" +
                    "Use case: event <name> /at <when>");
        }

        String[] splitParams = this.params.split("/at ", 2);
        if (splitParams.length != 2) {
            throw new DukeInputException("<" + this.params + "> is not valid for the 'event' command.\n" +
                    "Please add a /at timing to the task.");
        }
        return splitParams;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
