package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCommand extends Command {
    private final LocalDate date;

    public DateCommand(String dateString) {
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d-M-yyyy"));
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        TaskList timedTasks = list.getTimedTasks(this.date);
        String output = "Here are your tasks for " + formattedDate + ":\n";
        output += timedTasks;
        ui.printLine(output);
    }


}
