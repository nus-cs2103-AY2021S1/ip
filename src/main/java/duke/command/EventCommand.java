package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand implements Command {

    private final LocalDate eventTime;
    private final String eventDesc;

    public EventCommand(String eventDesc, LocalDate eventTime) {
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(eventDesc, eventTime);
        tasks.addTask(newTask);
        return ui.displayTaskAdd(newTask, tasks.getCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isMassCommand() {
        return false;
    }
}
