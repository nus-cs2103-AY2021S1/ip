package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SortCommand extends Command {

    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private String taskType;

    public SortCommand(String taskType) {
        this.taskType = taskType.toLowerCase();
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            if (taskType.equals(DEADLINE)) {
                ui.print(taskList.sortDeadline());
            } else if (taskType.equals(EVENT)) {
                ui.print(taskList.sortEvent());
            } else {
                throw new DukeException("Oops! I can only sort Deadlines or Events!");
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
