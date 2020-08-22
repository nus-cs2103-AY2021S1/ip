package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int taskDeleted) {
        this.taskIndex = taskDeleted;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NoSuchTaskException {
        Task taskDeleted = taskList.deleteTask(taskIndex);
        ui.show(String.format("\t Noted. I've removed this task:\n\t\t%s\n\t %s",
                taskDeleted.toString(),
                taskList.tasksRemaining()
        ));
    }
}
