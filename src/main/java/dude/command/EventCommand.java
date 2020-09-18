package dude.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import dude.task.Event;
import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The command creates an event and adds it to the tasklist when executed.
 */

public class EventCommand extends Command {
    private String description;
    private String at;

    /**
     * Constructor for the EventCommand class.
     * @param action action the command is to perform.
     * @param description description of the task.
     * @param at commencement date of the task.
     */
    public EventCommand(String action, String description, String at) {
        super(action);
        this.description = description;
        this.at = at;
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
            LocalDate date = LocalDate.parse(at);
            Event current = new Event(description, date);
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
