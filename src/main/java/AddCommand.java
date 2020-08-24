package main.java;

import java.io.IOException;

public class AddCommand extends Command {
    Task task;
    AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIOException {
        tasks.add(task);
        storage.appendToStorage(task.saveFormat() + System.lineSeparator());
        storage.flushWriter();
        ui.addTask(task);
    }
}
