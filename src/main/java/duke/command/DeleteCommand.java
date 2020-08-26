package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskNo);
        ui.showPrompt("Noted. I've removed this task:\n  "
                + task + "\n" + "Now you have " + tasks.getTasks().size()
                + (tasks.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
    }
}
