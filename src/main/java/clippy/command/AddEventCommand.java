package clippy.command;

import clippy.storage.Storage;
import clippy.task.Event;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class AddEventCommand extends AddCommand {
    private String at;

    public AddEventCommand(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(taskDescription, at);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }
}
