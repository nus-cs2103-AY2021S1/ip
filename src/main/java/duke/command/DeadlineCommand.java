package duke.command;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.TaskList;

/**
 * DeadlineCommand class handles instructions when user wants to create Deadline.
 */

public class DeadlineCommand extends Command {
    public DeadlineCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Adds a Deadline object to TaskList object.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.txt
     * @return A response saying deadline has been added
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String[] commandArr = command.split("\\s+");
            String instructions = commandArr[1];
            String date = commandArr[3];
            Deadline deadline;
            int counter = list.getList().size();
            if (commandArr.length == 5) {
                String tag = commandArr[4];
                if (!tag.startsWith("#")) {
                    return Warnings.invalidTagWarning();
                }
                deadline = new Deadline(false, counter + 1, instructions, date, tag);
            } else {
                deadline = new Deadline(false, counter + 1, instructions, date);
            }
            list.addTask(counter, deadline);
            storage.writeData(list.getList());
            String taskMessage = list.getList().get(counter).toString();
            return horizontalLine + "Okok. I help you add this task: \n"
                    + taskMessage + "\n" + "You got " + (counter + 1) + " tasks in the list.\n" + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidDeadlineWarning();
        }
    }
}
