package main.java;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public String taskName;
    public LocalDateTime date;

    public EventCommand(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new EventTask(taskName, date);
        tasks.addTask(task);
        storage.saveTaskToFile(task);
        String message = ui.addSuccess(task, tasks.size());
        ui.sendMessage(message);
    }
}
