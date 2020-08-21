package main.java.duke.command;


import java.io.IOException;
import main.java.duke.core.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        ui.showAdd(task, taskList.getSize());
        storage.writeRecord(taskList);
    }
}
