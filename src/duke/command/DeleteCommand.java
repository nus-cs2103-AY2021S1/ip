package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    String[] command;

    public DeleteCommand(String[] command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (command.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            Task task = taskList.delete(Integer.parseInt(command[1]));
            ui.showTaskDeleted(task);
            storage.writeToFile(taskList);
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong format.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
