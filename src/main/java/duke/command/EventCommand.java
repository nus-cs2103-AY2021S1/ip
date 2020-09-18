package duke.command;

import duke.command.Command;
import duke.task.Event;
import duke.task.TaskList;
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

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(desc, at);
        taskList.addTask(event);
        return (ui.showAddTask(event) + ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
