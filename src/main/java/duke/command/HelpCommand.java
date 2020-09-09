package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to the Help command.
 */
public class HelpCommand extends Command {

    /**
     * Creates a new instance of the Help command class.
     */
    public HelpCommand() {
    }

    /**
     * Displays the different commands available and their syntax on the GUI.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that is used by the instance of Duke.
     * @param ui UI object for the instance of Duke.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ui.printToConsole("Hi there! The following commands are available\n"
                + "todo {task name} - Create a new ToDo task\n"
                + "event {event name} /at {event date time} - Create a new Event task\n"
                + "deadline {deadline name} /by {deadline date time} - Create a new Deadline task\n"
                + "done {task ID} - Mark a task as done\n"
                + "delete {task ID} - Delete a task\n"
                + "find {keyword/task type/task status} - Filter tasks by a keyword\n"
                + "date {date} - Filter tasks that are due on a certain date\n"
                + "help - View help\n"
                + "bye - Leave app\n");
    }
}
