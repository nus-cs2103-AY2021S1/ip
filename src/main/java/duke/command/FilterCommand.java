package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to filter tasks by date.
 */
public class FilterCommand extends Command {
    private String date;

    public FilterCommand(String date) {
        this.date = date;
    }

    /**
     * Prints all tasks due on a specified date.
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException when invalid date is entered.
     * @return the Duke response to show user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String listItems = "";
            String reformatedDate = reformateDate(this.date);
            LocalDate filterDate = LocalDate.parse(reformatedDate);
            for (int i = 1; i <= taskList.size(); i++) {
                if (taskList.get(i - 1).isDate(filterDate)) {
                    listItems = listItems + "\n";
                    listItems = listItems + i + ". " + taskList.get(i - 1);
                }
            }
            String response = "Here are your tasks due on "
                    + filterDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ":" + listItems;
            return response;
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date!");
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date!");
        }
    }

    /**
     * Reformates date ot be readable by Java LocalDate API.
     * @param date in <dd/mm/yyyy> format
     * @return reformatedDate in <yyyy-mm-dd> format
     */
    private String reformateDate(String date) {
        String[] dateSplit = date.split("/", 3);
        String reformatedDate = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
        return reformatedDate;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
