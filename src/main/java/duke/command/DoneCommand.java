package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;

public class DoneCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException {
        int taskId = Parser.parseTaskId(input);

        if (taskId < 0 || taskId >= taskList.taskSize()) {
            throw new InvalidTaskIdException(taskId + 1 + "");
        }

        Task task = taskList.markAsDone(taskId);
        ui.markAsDone(task);
    }
}
