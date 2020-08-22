package main.java.duke.command;

import java.io.IOException;
import main.java.duke.core.Ui;
import main.java.duke.core.TaskList;
import main.java.duke.core.Storage;
import main.java.duke.handle.TaskNotFoundException;

/**
 * The DoneCommand class represents a command that marks a task as completed in the task list.
 */
public class DoneCommand extends Command {
    private int count;

    /**
     * Takes in the count of the task to be completed and returns a complete command.
     *
     * @param count The count of the task.
     */
    public DoneCommand(int count) {
        this.count = count;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and mark a task to be
     * completed in the task list and updates the local record using storage.
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
            taskList.markAsCompleted(count - 1);
            ui.showDone(taskList.getTask(count - 1), count);
            storage.writeRecord(taskList);
        }
    }
}
