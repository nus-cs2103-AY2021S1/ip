package main.java.command;


import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.IOException;


/**
 * DeleteAllCommand would execute the program when user specify
 * "delete all" as the command. This would automatically delete all
 * the existing task via TaskList, show message via Ui, and update the
 * external file via Storage.
 */
public class DeleteAllCommand extends Command {

    /**
     * Constructs a DeleteAllCommand without any
     * argument passed.
     */
    public DeleteAllCommand() {
        super();
    }

    /**
     * Executes the parsed user command. The result is:
     * 1. Delete all the tasks in the task list .
     * 2.show messages to the user via Ui object.
     * 3. Updates the external file via Storage object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        int length = tasks.size();
        for(int i = 0 ; i < length; i++) {
            tasks.remove(0);
        }

        ui.getMessageTemplate(ui.formatMessage("All of your task has been removed!"));

        storage.updateFile(tasks);
    }
}
