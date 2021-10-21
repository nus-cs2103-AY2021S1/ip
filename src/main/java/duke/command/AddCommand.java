package duke.command;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that will append a task to the list.
 * The command must be in the format of todo/deadline/event + empty spaces +
 * the description of the task (subject to the format requirement attached
 * to the task type).
 * Wrong format will trigger Exception.
 */
public class AddCommand extends Command {
    protected final String taskType;
    protected final String description;

    /**
     * Instantiates a AddCommand object
     * @param taskType an indication of the task type
     * @param description the detail information of the task
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType.trim();
        this.description = description.trim();
    }

    /**
     * Appends the task to the TaskList
     * @param list a TaskList that contains all the tasks
     * @param ui an object used to interact with users
     * @param storage an object used for retrieving data from or write data
     *                into a specific txt file on the hard disk
     * @throws DukeException if the user input does not follow the convention
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task current;
        try {
            switch (this.taskType) {
            case "todo": {
                current = new ToDo(this.description.trim());
                break;
            }
            case "deadline": {
                current = generateComplexTask("by");
                break;
            }
            case "event": {
                current = generateComplexTask("at");
                break;
            }
            default: {
                throw new DukeException("I don't understand you at all...");
            }
            }

            assert current != null : "The task pending to add is null";

            list.add(current);
            storage.appendTxt(current);
            return ui.showAdd(current, list);

        } catch (NumberFormatException | DateTimeException e) {
            throw new DukeException("Invalid date and time content detected!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid date and time format detected!");
        }
    }

    private Task generateComplexTask(String keyword) throws DukeException {
        String processedKeyword = " /" + keyword + " ";
        int index = this.description.indexOf(processedKeyword);
        if (index == -1) {
            throw new DukeException("Keyword " + processedKeyword + " not found, note the white space!");
        }

        assert index > 1 : "there is no task";

        String deadline = this.description.substring(index + 4).trim();
        String description = this.description.substring(0, index).trim();

        return keyword.equals("by") ? new Deadline(description, deadline)
                                    : new Event(description, deadline);

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
