package main.java.Command;

import main.java.Parser.Parser;
import main.java.Exception.NoIndexException;
import main.java.Storage.Storage;
import main.java.Task.Task;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.IOException;

/**
 * DeleteCommand would execute the program when user specify
 * "delete" as the command. This would automatically delete the task
 * specified by user in number via TaskList, show message of the deleted
 * task via Ui, and update the external file.
 */
public class DeleteCommand extends Command {

    private String command;


    /**
     * Constructs a DeleteCommand with the given
     * user command
     * @param command String user command
     */
    public DeleteCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes the parsed user command. The result is:
     * 1.Removes the tasks based on the index number specified
     * in the user command in the task list
     * 2.Shows messages of the deleted task to the user via Ui object,
     * 3.Updates the external file via Storage object.
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException This exception would be thrown when the system failed to detect
     * the external file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoIndexException {
        int index = Parser.findIndexParser(this.command);

        Task removedTask = tasks.remove(index-1);

        ui.getMessageTemplate(ui.formatMessage("Noted. I've removed this task:\n")
                + ui.formatMessage(removedTask.toString() + "\n")
                + ui.formatMessage("Now you have " + tasks.size() + " tasks in the list"));

        storage.updateFile(tasks);
    }
}
