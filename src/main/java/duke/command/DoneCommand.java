package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private String[] nextCommandArr;
    
    public DoneCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int doneTaskRef = Integer.parseInt(this.nextCommandArr[1]);
            Task doneTask = taskList.get(doneTaskRef - 1);
            doneTask.setDone();
            ui.doneText(doneTask);
        } catch (Exception e) {
            throw new DukeException("Please input a valid task number~");
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
