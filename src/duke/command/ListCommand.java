package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Processes the list command to list all the task(s) in the list.
     * @param taskList List of tasks.
     * @param ui UI of the bot.
     * @param storage Storage managing the file in hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //try {
        processList(taskList);
    }

    /**
     * Calls the TaskList to list all the task(s).
     * @param taskList TaskList that stores the list of tasks.
     */
    public void processList(TaskList taskList) { //throws DukeException {
        //try {
            taskList.listItems();
        //} catch (IndexOutOfBoundsException e) {
        //    throw new DukeException("Please input a valid number.");
        //}
    }
}
