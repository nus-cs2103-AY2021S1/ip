package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends TaskCreationCommand {
    public final static String COMMAND = "event";
    public final static String TIME_SPECIFIER = "/at";
    
    private String description;
    private String time;
    
    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, time);
        super.execute(task, ui, tasks);
    }
}
