package duke.command;

import duke.task.Task;
import duke.task.FixedDurationTask;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

public class StartTaskCommand implements Command {

    private final int index;
    private final String startAt;

    public StartTaskCommand(int index, String startAt) {
        this.index = index;
        this.startAt = startAt;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(index - 1);
            if (!(task instanceof FixedDurationTask)) {
                throw new DukeException("That is not a fixed duration task!");
            }
            FixedDurationTask fixedDurationTask = (FixedDurationTask) task;
            fixedDurationTask.setStartDateTimeFromString(startAt);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("start", task);
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
