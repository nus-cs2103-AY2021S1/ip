package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand implements Command {

    private final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        Task doneTask = tasks.markTaskAsDone(taskNum);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "OK! I have marked the following task as done:",
                doneTask.toString())), false, false);
    }
}
