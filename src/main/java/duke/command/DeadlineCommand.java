package duke.command;

import duke.*;
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
     * Create a Deadline with the user entered description and due-date String, store it in TaskList,
     * print feedback to user and store the new Deadline in Storage.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     * @throws DukeException if invalid date String provided
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WrongDateFormatException {
        LocalDateTime deadlineDateTime = DateParser.parseString(dateStr);
        Deadline deadline = new Deadline(description, deadlineDateTime);
        tasks.addTask(deadline);
        ui.printMessage(String.format("Okay, I've added the following deadline: \n %s", deadline.toString()));
        storage.updateTasks(tasks.getListOfTasks());
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
