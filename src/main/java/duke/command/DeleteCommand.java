package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * DeleteCommand class handles instructions when user wants to delete task
 */

public class DeleteCommand extends Command {
    public DeleteCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Deletes specified task
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     */
    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        String index = command.substring(command.length() - 1);
        int number = Integer.parseInt(index);
        String taskMessage = list.getList().get(number - 1).toString();
        list.deleteTask(number);
        System.out.println(hor_line + "Task deleted liao: \n" + taskMessage + "\n" +
                "You got " + list.getList().size() + " tasks left. \n" + hor_line);
        storage.writeData(list.getList());
    }
}
