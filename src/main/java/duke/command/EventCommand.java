package main.java.duke.command;

import main.java.duke.task.Event;
import main.java.duke.task.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDate;

public class EventCommand extends Command {
    String desc;
    LocalDate at;

    public EventCommand(String desc, LocalDate at) {
        this.desc = desc;
        this.at = at;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(desc, at);
        taskList.addTask(event);
        ui.showAddTask(event);
        ui.showNumberOfTasksLeft(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
