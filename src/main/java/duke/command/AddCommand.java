package main.java.duke.command;

import java.io.IOException;
import main.java.duke.core.Ui;
import main.java.duke.core.TaskList;
import main.java.duke.core.Storage;
import main.java.duke.task.Task;

/**
 * The AddCommand class represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Takes in a task to be added and returns a command.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and adds a task to
     * the task list and updates the local record using storage.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws IOException If the storage process needs to be handled
     */
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        ui.showAdd(task, taskList.getSize());
        storage.writeRecord(taskList);
    }
}
