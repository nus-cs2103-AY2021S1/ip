package duke.command;

import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class AddCommand implements Command {
    
    private final String[] parsedInput;
    private final String commandTag; // indicates what kind of Task
    
    public AddCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
        this.commandTag = parsedInput[0];
    }
    
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
