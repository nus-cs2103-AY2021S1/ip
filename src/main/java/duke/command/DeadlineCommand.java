package duke.command;

import duke.ui.Response;
import duke.ui.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.exceptions.WrongDateFormatException;
import duke.parser.DateParser;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDateTime;

/**
 * Command to create a Deadline Task. Created by using "deadline description /by DD MMM YYYY hhmm"
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String dateStr;

    public DeadlineCommand(String description, String dateStr) {
        this.description = description;
        this.dateStr = dateStr;
    }

    /**
     * Creates a Deadline with the user entered description and due-datetime String, store it in TaskList,
     * formats a feedback String to user and store the new Deadline in Storage.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @throws DukeException if invalid date String provided
     * @return Response object containing the formatted feedback String to be displayed by the GUI
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws WrongDateFormatException {
        LocalDateTime deadlineDateTime = DateParser.parseString(dateStr);
        Deadline deadline = new Deadline(description, deadlineDateTime);
        tasks.addTask(deadline);
        String message = ui.formatMessage(String.format("Okay, I've added the following deadline: \n %s",
                deadline.toString()));
        storage.updateTasks(tasks.getListOfTasks());
        return new Response(false, message);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeadlineCommand) {
            return this.description.equals(((DeadlineCommand) other).description) &&
                    this.dateStr.equals(((DeadlineCommand) other).dateStr);
        } else {
            return false;
        }
    }
}
