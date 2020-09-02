package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * DoneCommand class handles instructions when user wants to mark task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Marks task as done.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        String index = command.substring(5);
        try {
            int number = Integer.parseInt(index);
            if (number <= 0 || number > list.getList().size()) {
                return "Sorry hor, the number you keyed in is out of range...";
            } else {
                list.getList().set(number - 1, list.getList().get(number - 1).markDone());
                String taskMessage = list.getList().get(number - 1).toString();
                storage.writeData(list.getList());
                return horizontalLine + "Swee! Now I will mark this as done: \n"
                        + taskMessage + "\n" + horizontalLine;
            }
        } catch (NumberFormatException ex) {
            return "Sorry hor, the number you keyed in is invalid...";
        }
    }
}
