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
    public void execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        String index = command.substring(command.length() - 1);
        int number = Integer.parseInt(index) - 1;
        list.getList().set(number, list.getList().get(number).markDone());
        String taskMessage = list.getList().get(number).toString();
        System.out.println(horizontalLine + "Swee! Now I will mark this as done: \n"
                + taskMessage + "\n" + horizontalLine);
        storage.writeData(list.getList());
    }
}
