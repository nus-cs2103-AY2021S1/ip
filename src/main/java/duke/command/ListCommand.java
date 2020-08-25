package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * ListCommand shows user the tasks contained in TaskList object
 */
public class ListCommand extends Command {
    public ListCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Accesses TaskList object to iterate through the Tasks
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     */
    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        System.out.println(hor_line + "Here are the things you need to do lor: \n");
        System.out.println(list.iterateToDo() + hor_line);
    }
}
