package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private String[] nextCommandArr;
    
    public DeadlineCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.nextCommandArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty~");
        } else {
            try {
                String[] deadlineArr = nextCommandArr[1].split("/by");
                Deadline newDeadline = new Deadline(deadlineArr[0], deadlineArr[1].strip());
                taskList.add(newDeadline);
                ui.addTaskText(newDeadline, taskList);
            } catch (Exception e) {
                throw new DukeException("Please input a proper due date for your deadline~");
            }
        }
        
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
