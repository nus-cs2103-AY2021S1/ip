package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Todo;

/**
 * TodoCommand class handles instructions when user wants to create Todo
 */
public class TodoCommand extends Command {
    public TodoCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Adds a Todo object to TaskList object
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     */
    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        String instructions = command.substring(5);
        int counter = list.getList().size();
        list.addTask(counter, new Todo(false, counter + 1, instructions));
        String taskMessage = list.getList().get(counter).toString();
        System.out.println(horizontalLine + "Okok. I add for you: \n"
                + taskMessage + "\n" + "You got " + (counter + 1) + " tasks in the list.\n" + horizontalLine);
        storage.writeData(list.getList());
    }
}
