package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Todo;

/**
 * TodoCommand class handles instructions when user wants to create Todo.
 */
public class TodoCommand extends Command {
    public TodoCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Adds a Todo object to TaskList object.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.txt
     * @return A response saying todo has been added
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String[] commandArr = command.split("\\s+");
            String instructions = commandArr[1];
            int counter = list.getList().size();
            Todo todo;
            if (commandArr.length == 3) {
                String tag = commandArr[2];
                if (!tag.startsWith("#")) {
                    return Warnings.invalidTagWarning();
                }
                todo = new Todo(false, counter + 1, instructions, tag);
            } else {
                todo = new Todo(false, counter + 1, instructions);
            }
            list.addTask(counter, todo);
            String taskMessage = list.getList().get(counter).toString();
            storage.writeData(list.getList());
            return horizontalLine + "Okok. I add for you: \n"
                    + taskMessage + "\n" + "You got " + (counter + 1) + " tasks in the list.\n" + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidTodoWarning();
        }

    }
}
