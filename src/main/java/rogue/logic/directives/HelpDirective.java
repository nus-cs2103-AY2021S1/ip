package rogue.logic.directives;

import rogue.model.report.Report;
import rogue.model.task.Task;
import rogue.model.task.TaskList;
import rogue.storage.Storage;
import rogue.ui.Ui;

/**
 * Displays a set of instructions for the user to learn how to use {@code Rogue}.
 */
public class HelpDirective implements Executable {
    /**
     * Shows available actions and options that can be taken by {@code Rogue}.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     The {@code TaskList} from which {@code Task} should be listed.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} with a list of actions and their options
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder builder = new StringBuilder();
        builder.append("Only because you begged:\n");
        builder.append("\n");

        builder.append("Input format: Description\n");
        builder.append("An option marked ![] is mandatory, and *[] optional.\n");
        builder.append("\n");

        builder.append("1. help: lists all available actions and their options.\n");
        builder.append("2. todo ![/d description_String]: adds a ToDo with a description\n");
        builder.append("3. event ![/d description_String] ![/at date_dd-MM-yyyy]: adds an event"
                + " with a description and date.\n");
        builder.append("4. deadline ![/d description_String] ![/by date_dd-MM-yyyy]: adds a deadline"
                + " with a description and date.\n");
        builder.append("5. list: displays all stored tasks.\n");
        builder.append("6. delete ![/i index_int]: deletes the task with specified index.\n");
        builder.append("7. done ![/i index_int]: marks the task with specified index as done.\n");
        builder.append("8. find *[/d description_String] *[/days numOfDays_int]: searches for tasks"
                + " that match the description or is due in specified number of days;"
                + " both options can be combined for AND-search ; at least one option is needed.");

        return new Report(builder.toString());
    }
}
