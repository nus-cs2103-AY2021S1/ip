package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.storage.Storage;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddToDoCommand extends AddCommand {

    public AddToDoCommand(String taskDetails) {
        super(taskDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskDetails.isEmpty()) {
            throw new EmptyTaskException(TaskType.TODO);
        } else {
            addTask(new ToDo(taskDetails), tasks, ui, storage);
        }
    }
}
