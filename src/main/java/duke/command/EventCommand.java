package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDate;

public class EventCommand implements Command {

    private final LocalDate eventTime;
    private final String eventDesc;

    public EventCommand(String eventDesc, LocalDate eventTime) {
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(eventDesc, eventTime);
        tasks.addTask(newTask);
        ui.displayTaskAdd(newTask, tasks.getCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
