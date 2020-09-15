package duke.command;

import duke.error.DeadlineDateParseException;
import duke.error.EventDateParseException;
import duke.error.IncorrectFormat;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Represents a command which is used to add a task to the list.
 * It is executed when the execute method is called.
 *
 * @author Roger Lim
 */
public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the add command.
     * It will create and add the task as specified by the input when the command is created.
     * @param tasks The task list of the system.
     * @param ui The UI of the system which interacts with user.
     * @param storage The storage of the system.
     * @throws IncorrectFormat
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws IncorrectFormat, EventDateParseException, DeadlineDateParseException {
        try {
            int indexOfSpace = input.indexOf(" ");
            int indexOfSlash = input.indexOf("/");
            int length = input.length();
            Task newTask;
            String output = "";
            switch (input.substring(0, indexOfSpace)) {
            case "todo":
                newTask = new ToDo(input.substring(indexOfSpace, length));
                output += ui.printNew(newTask, "ToDo", tasks.numTask() + 1);
                break;
            case "deadline":
                String dateStringDeadline = input.substring(indexOfSlash + 1, input.length());
                newTask = new Deadline(input.substring(indexOfSpace, indexOfSlash), dateStringDeadline);
                output += ui.printNew(newTask, "Deadline", tasks.numTask() + 1);
                break;
            case "event":
                String dateStringEvent = input.substring(indexOfSlash + 1, input.length());
                newTask = new Event(input.substring(indexOfSpace, indexOfSlash), dateStringEvent);
                output += ui.printNew(newTask, "Event", tasks.numTask() + 1);
                break;
            default:
                throw new IncorrectFormat();
            }
            tasks.addTask(newTask, storage);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectFormat();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
