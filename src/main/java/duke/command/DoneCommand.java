package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Task;

public class DoneCommand extends ComplexCommand {

    public DoneCommand(String params) {
        super(params);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            // Attempt to parse parameter and get task at given index
            int index = this.parseParams(taskManager.size());

            Task temp = taskManager.getTask(index-1);

            // Mark task as done
            temp.doTask();

            // Display status message to user
            ui.display("Nice! I've marked this task as done:");
            ui.display("\t" + temp.toString());

        } catch (DukeInputException e) {
            ui.displayException(e);
        }

    }

    public int parseParams(int taskManagerSize) throws DukeInputException {

        // Check if parameters are empty
        if (this.params.equals("")) {
            throw new DukeInputException("'done' requires parameters.\n"
                    + "Use case: done <task number>");
        }

        // Check if numerical parameter is given
        int index;
        try {
            index = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <"
                    + this.params + "> after a 'done' command!");
        }

        // Check if index is valid
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
