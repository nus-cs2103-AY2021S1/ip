package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Processes the list command to list all the task(s) in the list.
     *
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //try {
        return processList(taskList);
    }

    /**
     * Calls the TaskList to list all the task(s).
     *
     * @param taskList TaskList that stores the list of tasks.
     */
    public String processList(TaskList taskList) { //throws DukeException {
        //try {
        return taskList.listItems();
        //} catch (IndexOutOfBoundsException e) {
        //    throw new DukeException("Please input a valid number.");
        //}
    }
}
