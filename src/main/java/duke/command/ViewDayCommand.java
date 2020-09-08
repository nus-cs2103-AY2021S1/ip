package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private LocalDate parseDate() {
        return LocalDate.from(dateFormatter.parse(dateTimeData));
    }
}
