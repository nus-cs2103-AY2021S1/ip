package duke.command;

import duke.Deadline;
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
     * @param storage Storage object that reads and writes to duke.ser
     * @return A response saying deadline has been added
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String instructions = command.substring(9);
            String[] arr = instructions.split("/by");
            instructions = arr[0].substring(0, arr[0].length() - 1);
            String date = arr[1].substring(1);
            int counter = list.getList().size();
            list.addTask(counter, new Deadline(false, counter + 1, instructions, date));
            storage.writeData(list.getList());
            String taskMessage = list.getList().get(counter).toString();
            return horizontalLine + "Okok. I help you add this task: \n"
                    + taskMessage + "\n" + "You got " + (counter + 1) + " tasks in the list.\n" + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidDeadlineWarning();
        }
    }
}
