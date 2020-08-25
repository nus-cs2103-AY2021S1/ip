package duke.command;

import duke.component.*;
import duke.task.Task;

public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("The task index should be an index on the list!");
        }
        Task task = taskList.get(index);
        taskList.markAsDone(index);
        storage.saveList(taskList);

        ui.giveResponse("Nice! I've marked this task as done:\n       " + task);
    }
}
