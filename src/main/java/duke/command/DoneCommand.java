package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

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
