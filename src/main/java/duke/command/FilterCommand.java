package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FilterCommand extends Command {
    private String date;

    public FilterCommand(String date) {
        this.date = date;
    }

    /**
     * Prints all tasks due on a specified date.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     * @return the Duke response to show user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String listItems = "";
            // Reformat the date to be readable by LocalDate
            String[] dateSplit = date.split("/", 3);
            String reformatedDate = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
            LocalDate filterDate = LocalDate.parse(reformatedDate);
            for (int i = 1; i <= taskList.size(); i++) {
                if (taskList.get(i - 1).isDate(filterDate)) {
                    listItems = listItems + "\n";
                    listItems = listItems + i + ". " + taskList.get(i - 1);
                }
            }
            String response = "Here are your tasks due on " + filterDate.format(DateTimeFormatter.ofPattern("MMM d yyy"))
                    + ":" + listItems;
            return response;
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date!");
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
