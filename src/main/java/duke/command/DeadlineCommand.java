package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.exceptions.WrongDateFormatException;
import duke.parser.DateParser;
import duke.storage.DukeStateManager;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Command to create a Deadline Task. Created by using "deadline description /by DD MMM YYYY hhmm"
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String dateStr;

    /**
     * Constructs a DeadlineCommand object which represents a Command to create
     * a Deadline task with the given description and date String.
     *
     * @param description description of the Deadline to be created
     * @param dateStr String to describe the datetime of the Deadline
     */
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
     * @param dukeStateManager DukeStateManager to manage the current state of Duke
     * @return Response object containing the formatted feedback String to be displayed by the GUI
     * @throws WrongDateFormatException if invalid date String provided
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage,
                            DukeStateManager dukeStateManager) throws WrongDateFormatException, IOException {
        LocalDateTime deadlineDateTime = DateParser.parseString(dateStr);
        Deadline deadline = new Deadline(description, deadlineDateTime);

        tasks.addTask(deadline);
        String message = ui.formatMessage(String.format("Okay, I've added the following deadline: \n %s",
                deadline.toString()));
        storage.updateTasks(tasks.getListOfTasks());

        this.storeState(dukeStateManager, tasks, storage);

        return new Response(false, message);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeadlineCommand) {
            return this.description.equals(((DeadlineCommand) other).description)
                    && this.dateStr.equals(((DeadlineCommand) other).dateStr);
        } else {
            return false;
        }
    }
}
