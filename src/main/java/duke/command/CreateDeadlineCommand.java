package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Deadline;

import java.time.LocalDate;

/**
 * Encapsulates a command to create a deadline
 */
public class CreateDeadlineCommand extends Command {

    /** Date of the deadline */
    private final LocalDate date;

    /** Description of the deadline */
    private final String description;

    /** Initial completion status of the deadline */
    private final boolean isComplete;

    /**
     * Constructor
     *
     * @param description Description of the deadline
     * @param isComplete Initial completion status of the deadline
     * @param date Date of the deadline
     */
    public CreateDeadlineCommand(String description, boolean isComplete, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Executes the command to create a deadline.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive Archive
     * @param ui Ui
     * @return Output strings displayed in the UI showing deadline creation
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert storage != null;
        assert taskList != null;
        assert ui != null;

        Deadline newDeadline = taskList.addDeadline(description, isComplete, date);
        return ui.getCreateTaskStrings(taskList, newDeadline);
    }
}
