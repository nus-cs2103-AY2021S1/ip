package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.ui.Ui;


/**
 * Deletes Tasks from the TaskList and invokes appropriate UI messages about it
 */
public class DeleteCommand implements Command {
    private final String[] parsedInput;
    public DeleteCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
    }
    /**
     * Prints out a deletion message of the command, removes the entry from TaskList and displays the current status of
     * the TaskList
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     *
     * @throws DukeException If we can't write onto the file after deleting a task to TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        int taskID = Integer.parseInt(this.parsedInput[1]);
        lines.add(Message.DELETE_MSG.getMsg());
        lines.add(tasks.deleteTask(taskID));
        lines.add(tasks.getCurrentStatus());
        ui.display(lines);
        Storage.save(tasks);
        return Command.listLinesToString(lines);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
