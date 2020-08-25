package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {

    protected static final String DONE_MESSAGE = "Nice! I've marked this task as done: ";

    private int doneIndex;

    private DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    public static DoneCommand parse(String command) {
        String[] details = command.split(" ", 2);
        if (details.length == 1) {
            throw new DukeException("Please specify a task to complete!");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number input!");
        }
        return new DoneCommand(taskNumber);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (doneIndex > taskList.size()) {
            throw new DukeException("No such task!");
        }
        Task doneTask = taskList.get(doneIndex);
        doneTask.markDone();
        ui.outputMessage(createDoneMessage(doneTask));
        storage.updateFile(taskList);
    }

    private String createDoneMessage(Task taskDone) {
        return DONE_MESSAGE + "\n   " + taskDone;
    }






}
