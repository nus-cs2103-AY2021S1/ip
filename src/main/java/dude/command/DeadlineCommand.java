package dude.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import dude.task.Deadline;
import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The command creates a deadline and adds it to the tasklist when executed.
 */

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructor for the DeadlineCommand class.
     *
     * @param action action the command is to perform.
     * @param description description of the task.
     * @param by deadline of the task.
     */
    public DeadlineCommand(String action, String description, String by) {
        super(action);
        this.description = description;
        this.by = by;
    }

    /**
     * Adds the task to the TaskList, stores the data and displays the resulting output.
     *
     * @param tasks TaskList containing all the current tasks.
     * @param ui Ui class to display output.
     * @param storage Storage class to store tasks.
     * @throws CommandException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        try {
            StringBuilder uiOutput = new StringBuilder();
            LocalDate date = LocalDate.parse(by);
            Deadline current = new Deadline(description, date);
            int findDuplicate = tasks.detectDuplicates(current);
            if (findDuplicate != super.FALSE) {
                uiOutput.append("Duplicate task found at position ").append(findDuplicate).append("!");
                ui.setMessage(uiOutput.toString());
                return;
            }
            tasks.addTask(current);
            uiOutput.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(tasks.getCount()).append(" tasks in the list.");
            ui.setMessage(uiOutput.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (DateTimeException e) {
            throw new CommandException("Sorry, Invalid date format!");
        }
    }
}
