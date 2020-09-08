package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.ExceptionType;

public class DoneCommand extends Command {
    public DoneCommand() {
        super();
    }

    public String execute(int index, TaskList userTasks, Storage storage) {
        try {
            if (index >= userTasks.getTaskListSize()) {
                throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
            } else {
                userTasks.markTaskAsDone(index);
                response = new Ui().markAsDoneMessage(userTasks.getTask(index));
            }
            storage.saveToFile(userTasks.getTaskList());
        } catch (DukeException ex) {
            response = new Ui().errorMessage(ex);
        }
        return getResponse();
    }
}