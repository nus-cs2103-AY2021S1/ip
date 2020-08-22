package main.java.duke.command;

import java.io.IOException;
import main.java.duke.core.Ui;
import main.java.duke.core.TaskList;
import main.java.duke.core.Storage;
import main.java.duke.task.Task;
import main.java.duke.handle.TaskNotFoundException;

/**
 * The DeleteCommand class represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int count;

    /**
     * Takes in the count of the task to be deleted and returns a delete command.
     *
     * @param count The count of the task.
     */
    public DeleteCommand(int count) {
        this.count = count;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and deletes a task from
     * the task list and updates the local record using storage.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled
     */
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        if(!taskList.has(count - 1)) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task");
        } else {
            Task task = taskList.remove(count - 1);
            ui.showDelete(task, count, taskList.getSize());
            storage.writeRecord(taskList);
        }
    }
}
