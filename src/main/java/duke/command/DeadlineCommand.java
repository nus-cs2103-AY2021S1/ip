package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {
    private String taskName;
    private String deadlineString;
    private String response;

    /**
     * Object representing Commands that refer to Deadline tasks
     * @param taskName Name of Deadline task
     * @param deadlineString String representing deadline
     */
    public DeadlineCommand(String taskName, String deadlineString) {
        this.taskName = taskName;
        this.deadlineString = deadlineString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();

        Task task = new Deadline(taskName, deadlineString);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        Task task = new Deadline(taskName, deadlineString);
        tasks.addTask(task);
        return ui.getAddTaskResponseAsString(task, tasks.numTasks());
    }

    @Override
    public void undo(TaskList tasks) {
        tasks.removeLastTask();
    }

    private void verifyInput() throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badDeadlineTask();
        } else if (deadlineString.isBlank()) {
            throw DukeException.badDeadlineDate();
        }
    }
}
