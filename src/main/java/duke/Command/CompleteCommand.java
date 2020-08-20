package duke.Command;

import duke.*;

public class CompleteCommand extends Command {

    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.saveTasks(taskList);
        ui.showCompletionMessage(task);
    }
}
