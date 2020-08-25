package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.time.LocalDate;

public class EventCommand extends Command {
    private String description;
    private LocalDate time;

    public EventCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(this.description, false, this.time);
        tasks.addTask(newTask);
    }
}
