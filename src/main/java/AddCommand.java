package main.java;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    void excecute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        ui.showAdd(task, taskList.getSize());
        storage.writeRecord(taskList);
    }
}
