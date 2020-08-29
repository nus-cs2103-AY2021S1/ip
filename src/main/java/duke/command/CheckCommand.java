package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * Implements the <code>Command</code> interface. <code>CheckCommand</code> runs
 * a search in the list of <code>Task</code> for <code>Task</code> occurring on this
 * specific date as specified by user.
 */
public class CheckCommand implements Command {

    /**
     * Executes a command to run a search on the list of <code>Task</code> for <code>Task</code>
     * occurring on this specific date.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @throws DukeException if system fails to execute search
     */
    public String execute(String command, Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            command = command.substring(5);
            String[] inputDate = command.trim().split("/");
            String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
            LocalDate dateFormat = LocalDate.parse(formatDate);
            List<Task> sameDates = taskList.checkDate(dateFormat);

            if (sameDates.isEmpty()) {
                return " You have no task on this day! Have a good break! *Woof*\n";
            } else {
                StringBuilder print = new StringBuilder(" Here is the list of ongoing tasks on "
                        + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(dateFormat) + ":\n");
                for (Task t : sameDates) {
                    print.append("   ").append(sameDates.indexOf(t) + 1).append(".")
                            .append(t.toString()).append("\n");
                }
                return print.toString();
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.inputCorrectCheckDateFormat());
        }
    }

    /**
     * Compares this <code>CheckCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>CheckCommand</code> against
     * @return true if the given object is an instance of this <code>CheckCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof CheckCommand;
        }
    }
}
