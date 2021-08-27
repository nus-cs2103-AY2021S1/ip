package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.task.Task;

public class ListCommand extends Command {
    public ListCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException {
        if (this.getInputCommand().split(" ").length != 1) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'list' command parameters!");
        }

        if (list.getList().size() == 0) {
            ui.printMessage("There is nothing in the list!");
        } else {
            ui.printMessage("Here are the tasks in your list:");

            int taskIndexCounter = 1;
            for (Task task: list.getList()) {
                ui.printMessage("" + taskIndexCounter + "." + task);
                taskIndexCounter++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
