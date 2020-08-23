package duke.command;

import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doTask(taskNumber);
        storage.save(tasks);
        ui.printMessage("Nice! I've marked this task as done:\n\t   " +
                tasks.getTask(taskNumber));
    }

    @Override
    public boolean getIsExit() {
        return isExit;
    }
}
