package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ViewDayCommand extends Command {
    private String dateTimeData;
    private DateTimeFormatter dateFormatter;

    public ViewDayCommand(String dateTimeData, DateTimeFormatter dateFormatter) {
        this.dateTimeData = dateTimeData;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDate date = parseDate();
        return tasks.ViewScheduleOfDay(ui, date);
    }

    private LocalDate parseDate() throws DukeException {
        try {
            return LocalDate.from(dateFormatter.parse(dateTimeData));
        } catch (DateTimeParseException error) {
            throw new DukeException("It seems like you've provided us "
                    + "with the wrong date format for your event. \uD83D\uDE1E"
                    + "\n\nPlease structure it as dd.mm.yy where d refers to day, m refers to month"
                    + "and y refers to year"
                    + "\n\nFor example, 05.02.20 1200 represents 5th Feb 2020, 12pm");
        }
    }
}
