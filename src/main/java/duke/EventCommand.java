package duke;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Constructs an event command to add an event task.
     * 
     * @param input String input of the event task.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        assert input.length() > 5 : "Please use the format: event (DESCRIPTION) /at (YYYY-MM-DD)";
        Task newTask;
        try {
            String[] taskParams = input.substring(7).split("/at ");
            String eventDescription = taskParams[0];
            LocalDate eventTiming = LocalDate.parse(taskParams[1]);
            newTask = new Event(eventDescription, eventTiming);
            taskList.addTask(newTask);
            store.writeFile(taskList);
            return ui.showAddition(newTask, taskList);
        } catch (DateTimeException | DukeException error) {
            throw new DukeException("Please use the format: event (DESCRIPTION) /at (YYYY-MM-DD)");
        }
    }
}