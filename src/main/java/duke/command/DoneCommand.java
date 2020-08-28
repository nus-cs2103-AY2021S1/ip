package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command{
    private final int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("The task number is not found");
        }
        taskList.doneTask(taskNumber);
        // display update task success message
        String response = "Nice! I've marked this task as done:";
        response += "\n\t\t".concat(taskList.getTask(taskNumber).toString());
        ui.printResponse(response);
        // update task data in the file
        storage.writeFile(taskList);
    }
}
