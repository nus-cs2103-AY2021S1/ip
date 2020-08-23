package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.EventTask;
import duke.task.Task;

public class EventCommand implements Command {

    private final String description;
    private final String timePeriod;

    public EventCommand(String description, String timePeriod) {
        this.description = description;
        this.timePeriod = timePeriod;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        Task eventTask = new EventTask(description, timePeriod);
        tasks.add(eventTask);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "Sure! I have added the following event task to your list: ",
                eventTask.toString(),
                tasks.getListStatus())), false, false);
    }
}