package duke.command;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.ui.Ui;

public class CommandDelete extends Command {

    public static final String COMMAND_STRING = "delete";

    private int toDeleteIdx;

    public CommandDelete(TaskList taskList, Ui ui, int toDeleteIdx) {
        super(taskList, ui);
        this.toDeleteIdx = toDeleteIdx;
    }

    @Override
    public void execute() throws DukeException {
        String output = "Feeling weak? Giving up? Well done... Removed this task: \n";
        output += taskList.remove(toDeleteIdx) + "\n";
        ui.outputBlockToUser(output);
    }
}
