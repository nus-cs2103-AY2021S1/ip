package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Task;

public class DeleteCommand extends ComplexCommand {

    public DeleteCommand(String params) {
        super(params);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            // Attempt to parse parameters then remove task with given index
            int index = this.parseParams(taskManager.size());
            Task temp = taskManager.removeTask(index-1);

            // Display status message to user
            ui.display("Alright. I've removed this task:");
            ui.display("\t" + temp.toString());
            ui.display("Now you have " + taskManager.size() + " tasks in the list.");

        } catch (DukeInputException e) {
            ui.displayException(e);
        }

    }

    public int parseParams(int taskManagerSize) throws DukeInputException {

        // Check if parameters are empty
        if (this.params.equals("")) {
            throw new DukeInputException("'delete' requires parameters.\n"
                    + "Use case: delete <task number>");
        }

        // Check if given parameter is numerical
        int index;
        try {
            index = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <"
                    + this.params + "> after a 'delete' command!");
        }

        // Check if given index is valid
        if (index < 1 || index > taskManagerSize) {
            throw new DukeInputException("Index input out of range");
        }

        return index;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
