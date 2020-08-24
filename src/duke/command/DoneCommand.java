package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class DoneCommand implements Command {
    String[] command;

    public DoneCommand(String[] command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (command.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            Task task = taskList.markAsDone(Integer.parseInt(command[1]));
            ui.showTaskMarkedDone(task);
            storage.writeToFile(taskList);
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong format.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
