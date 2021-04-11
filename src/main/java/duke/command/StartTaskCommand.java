package duke.command;

import duke.task.FixedDurationTaskWithDateTime;
import duke.task.Task;
import duke.task.FixedDurationTask;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * This command is in charge of setting date time values to
 * fixed duration tasks.
 */
public class StartTaskCommand implements Command {

    private final int index;
    private final String startAt;

    /**
     * Constructs the start task command which will set a date time
     * to the given task.
     * @param index the task number.
     * @param startAt the raw string of date time.
     */
    public StartTaskCommand(int index, String startAt) {
        this.index = index;
        this.startAt = startAt;
    }

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(index - 1);
            if (!(task instanceof FixedDurationTask)) {
                throw new DukeException("That is not a fixed duration task!");
            }

            // Creating FixedDurationTaskWithDateTime using previous task description
            FixedDurationTask fixedDurationTask = (FixedDurationTask) task;
            String[] rawDesc = fixedDurationTask.getDescription().split(" / ");
            String description = rawDesc[0];
            int duration = Integer.parseInt(rawDesc[1]);
            Task newTask = new FixedDurationTaskWithDateTime(description, duration, startAt);

            // Replacing the task and sending response message
            tasks.replace(index - 1, newTask);
            storage.update(tasks.getList());
            String msg = ui.getTaskSuccessMessage("start", newTask);
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
