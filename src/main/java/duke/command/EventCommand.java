package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.Event;
import main.java.duke.task.TaskList;
import main.java.duke.Ui;
import main.java.duke.Storage;

import java.time.LocalDate;

public class EventCommand extends Command {
    String desc;
    LocalDate at;

    public EventCommand(String desc, LocalDate at) {
        this.desc = desc;
        this.at = at;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(desc, at);
        taskList.addTask(event);
        return (ui.showAddTask(event) + ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
