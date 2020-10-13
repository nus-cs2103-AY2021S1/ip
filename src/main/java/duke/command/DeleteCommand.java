package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeIndexException;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents an DeleteCommand that is part of the Command class, regarding deleting a task.
 */

public class DeleteCommand extends Command {
    private final int num;
    private String deletedMessage = "";

    /**
     * The constructor for the Delete Command.
     * @param command the specific command instructions
     * @param num the task number
     */
    public DeleteCommand(String command, int num) {
        super(command, false);
        this.num = num;
    }

    /**
     * Executes the command to delete a task from the list of tasks.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (num < 0 || num > list.size()) {
                throw new DukeIndexException(ui.invalidIndexMessage());
            } else {
                Task deleted = list.delete(num - 1);
                deletedMessage = deleted.toString();
                ui.deleteMessage(deleted.toString(), list.size());
                storage.deleteTask(num);
            }
        } catch (DukeException | IOException e) {
            ui.errorEncounter(e);
        }
    }

    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        System.out.println("list size: " + list.size());
        try {
            if (num < 0 || num > list.size()) {
                throw new DukeIndexException(ui.invalidIndexMessage());
            } else {
                return ui.deleteMessage(deletedMessage, list.size(), true);
            }
        } catch (DukeIndexException e) {
            return e.getMessage();
        }
    }
}
