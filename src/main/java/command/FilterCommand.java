package command;

import exception.DukeException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

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
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String output = "";
            String[] dateSplit = date.split("/", 3);
            String reformatedDate = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
            LocalDate filterDate = LocalDate.parse(reformatedDate);
            for (int i = 1; i <= taskList.size(); i++) {
                if (taskList.get(i - 1).isDate(filterDate)) {
                    output = output + i + ". " + taskList.get(i - 1) + "\n";
                }
            }
            System.out.println(ui.LINE + "Here are your task due on "
                    + filterDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ": \n" + output + ui.LINE);
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.LINE + "Invalid input! Please enter a valid date! \n" + ui.LINE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.LINE + "Invalid input! Please specify which date you want to filter! \n" + ui.LINE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
