package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.CalendarException;

public class ShowCommand extends Command {

    public ShowCommand(String task) {
        super(task);
    }

    /**
     * Processes the show command to show all the task(s) on that day.
     *
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return processShow(this.task, taskList, ui, storage);
        } catch (CalendarException cal) {
            return cal.getMessage();
        }
    }

    /**
     * Calls the TaskList to show all the task(s) on that day.
     *
     * @param date     Queried date.
     * @param taskList TaskList containing the tasks list.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     * @throws CalendarException If the date is in incorrect format.
     */

    public String processShow(
        String date, TaskList taskList, Ui ui, Storage storage) throws CalendarException {
        return taskList.showDate(date);
    }

    /**
     * Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
     *
     * @param other Other object to compare.
     * @return True if this object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ShowCommand) {
            ShowCommand showCommand = (ShowCommand) other;
            return this.task.equals(showCommand.getTask());
        }
        return false;
    }
}
