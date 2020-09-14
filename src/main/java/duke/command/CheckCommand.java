package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Implements the <code>Command</code> interface. <code>CheckCommand</code> runs
 * a search in the list of <code>Task</code> for <code>Task</code> occurring on this
 * specific date as specified by user.
 */
public class CheckCommand implements Command {
    private final String command;

    public CheckCommand(String command) {
        this.command = command;
    }
    /**
     * Executes a command to run a search on the list of <code>Task</code> for <code>Task</code>
     * occurring on this specific date.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to execute search
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            String parseCommand = command.substring(5);
            String[] inputDate = parseCommand.trim().split("/");
            String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
            LocalDate dateFormat = LocalDate.parse(formatDate);
            List<Task> sameDates = taskList.checkDate(dateFormat);

            if (sameDates.isEmpty()) {
                return " You have no task on this day! Have a good break! *Woof*\n";
            } else {
                StringBuilder print = new StringBuilder(" Here is the list of ongoing tasks on "
                        + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(dateFormat) + ":\n");

                for (Task t : sameDates) {
                    print.append("   ").append(sameDates.indexOf(t) + 1).append(". ")
                            .append(t.toString()).append("\n");
                }

                return print.toString();
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            String message = " Please enter date in YYYY/MM/DD format! *Woof woof*\n";
            throw new DukeException(message);
        }
    }

    /**
     * Returns a string representation informing user how to execute this command.
     *
     * @return a string representation informing users how to execute this command
     */
    public static String commandToExecute() {
        return " check <YYYY/MM/DD> : tasks on this day\n";
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
