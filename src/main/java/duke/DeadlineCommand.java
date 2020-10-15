package duke;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Constructs a deadline command to add an event task.
     *
     * @param input String input of the deadline task.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        assert input.length() > 7 : "Please use the format: deadline (DESCRIPTION) /by (YYYY-MM-DD)";
        Task newTask;
        try {
            String[] taskParams = input.substring(9).split("/by ");
            String deadlineDescription = taskParams[0];
            LocalDate deadlineTiming = LocalDate.parse(taskParams[1]);
            newTask = new Deadline(deadlineDescription, deadlineTiming);
            taskList.addTask(newTask);
            store.writeFile(taskList);
            return ui.showAddition(newTask, taskList);
        } catch (DateTimeException | DukeException error) {
            throw new DukeException("Please use the format: deadline (DESCRIPTION) /by (YYYY-MM-DD)");
        }
    }
}