package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {

    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task delTask = tasks.delete(taskNum);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "OK! I have deleted the following task for your list:",
                delTask.toString(),
                tasks.getListStatus())), false, false);
    }
}
