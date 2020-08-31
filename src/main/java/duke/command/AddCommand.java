package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.ui.Ui;


/**
 * Adds Tasks to the TaskList and invokes appropriate UI messages about it
 */
public class AddCommand implements Command {
    private final String[] parsedInput;
    private final String commandTag; // indicates what kind of Task
    /**
     * Constructs an AddCommand Object.
     *
     * @param parsedInput Parser's output for the user input
     */
    public AddCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
        this.commandTag = parsedInput[0];
    }
    /**
     * Prints out a confirmation message of the command, adds the entry to TaskList and displays the current status of
     * the TaskList
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     *
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.CONFIRMATION_MSG.getMsg());
        String reply = tasks.addEntry(this.parsedInput, this.commandTag);
        lines.add(reply);
        lines.add(tasks.getCurrentStatus());
        ui.display(lines);
        Storage.save(tasks); // save upon addition
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
