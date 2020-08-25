package duke.command;

import duke.exception.*;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FindByDateCommand extends Command {

    public FindByDateCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String date = parsedCommand[1].trim();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate dateToSearch = LocalDate.parse(date, dateFormatter);
            int index = 1;
            ui.printReply("Search Results:");
            for (Task task : tasks.getTaskList()) {
                if (task.getDate() != null) {
                    if (task.getDate().isEqual(dateToSearch)) {
                        String results = String.format("%d. %s", index, task);
                        ui.printReply(results);
                        index++;
                    }
                }
            }
            if (index == 1) {
                ui.printReply("No tasks found! Please search using a different date!");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No task date provided. Please input a valid date using the format: 'dd/mm/yyyy' \n" +
                    "Type '/commands' to view the correct command for task search by date! ";
            throw new InvalidFunctionException(err);
        } catch (DateTimeParseException ex) {
            String err = "The task date format is incorrect. Please input a valid date using the format: 'dd/mm/yyyy'";
            throw new InvalidFunctionException(err);
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
