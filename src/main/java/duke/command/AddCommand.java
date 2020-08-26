package duke.command;

import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Adds Tasks to the TaskList and invokes appropriate UI messages about it
 */
public class AddCommand implements Command {
    
    private final String[] parsedInput;
    private final String commandTag; // to tell what kind of new entry this is:
    
    public AddCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
        this.commandTag = parsedInput[0];
    }
    
    /**
     * Prints out a confirmation message of the command, adds the entry to TaskList and displays the current status
     * of the TaskList
     * @param tasks Current TaskList
     * @param ui Where the User shall receive messages about the command
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.CONFIRMATION_MSG.getMsg());
        String reply = tasks.addEntry(this.parsedInput, this.commandTag);
        lines.add(reply);
        lines.add(tasks.getCurrentStatus());
        ui.display(lines);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
