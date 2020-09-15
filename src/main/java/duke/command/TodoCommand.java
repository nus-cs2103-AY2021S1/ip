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
            String shortCommand = command.substring(5);
            String[] commandArr = shortCommand.split("#");
            String instructions;
            if (commandArr[0].endsWith(" ")) {
                int endIndex = commandArr[0].length() - 1;
                instructions = commandArr[0].substring(0, endIndex);
            } else {
                instructions = commandArr[0];
            }
            int counter = list.getList().size();
            Todo todo;
            if (commandArr.length == 2) {
                String tag = "#" + commandArr[1];
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
