package duke.command;

import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    
    private final String[] parsedInput;
    
    public DeleteCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ArrayList<String> lines = new ArrayList<>();
        int taskID = Integer.parseInt(this.parsedInput[1]);
        lines.add(Message.DELETE_MSG.getMsg());
        lines.add(tasks.deleteTask(taskID));
        lines.add(tasks.getCurrentStatus());
        ui.display(lines);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
