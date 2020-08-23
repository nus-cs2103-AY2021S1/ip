package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskAfterCommand extends Command {
    private String date;

    public TaskAfterCommand(String date) {
        this.date = date;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDate parsedDate;

        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }

        String message = ui.eachTaskAfter(parsedDate, tasks.getTasks());
        ui.sendMessage(message);
    }
}
