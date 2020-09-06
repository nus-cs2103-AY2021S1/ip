package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandDelete extends Command {

    public static final String COMMAND_STRING = "delete";

    private int toDeleteIdx;

    /**
     * Constructor for CommandDelete.
     * @param taskList task list.
     * @param ui ui.
     * @param toDeleteIdx index of task to delete.
     */
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
