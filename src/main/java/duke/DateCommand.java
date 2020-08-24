package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a "get tasks at a specific date" command in Duke.
 */
public class DateCommand extends Command {
    private final LocalDate date;

    /**
     * Creates a new DateCommand instance.
     *
     * @param dateString string representing date of tasks to be retrieved.
     */
    public DateCommand(String dateString) {
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d-M-yyyy"));
    }

    /**
     * Executes the date command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        TaskList timedTasks = list.getTimedTasks(this.date);
        String output = "Here are your tasks for " + formattedDate + ":\n";
        output += timedTasks;
        ui.printLine(output);
    }


}
