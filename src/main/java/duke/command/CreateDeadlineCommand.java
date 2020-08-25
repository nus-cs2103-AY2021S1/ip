package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

import main.java.duke.task.Deadline;

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
     * Executes the command to create a deadline
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Deadline deadline = tasks.addDeadline(this.description, this.isComplete, this.date);
        ui.printCreateTask(tasks, deadline);
    }
}
