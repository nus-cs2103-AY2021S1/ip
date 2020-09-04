package duke.command;

import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Todo;

/**
 * ToDo command type.
 * Create todo task and add into task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class ToDoCommand extends Command {

    private final String fullCommand;

    /**
     * Class constructor.
     * Extract task details from full command.
     *
     * @param fullCommand full command input by user.
     */
    public ToDoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Create todo task and add to task arraylist. Write to file.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String description = fullCommand.substring(fullCommand.indexOf(" ")).trim();
        StringBuilder resultSb = new StringBuilder(
                String.format("%s\n %s\n", ui.showLine(), "Got it. I've added this task:"));
        taskList.addTask(new Todo(description));
        resultSb.append("\t").append(taskList.retrieveTask(taskList.sizeOfList() - 1)).append("\n");
        resultSb.append(String.format("Now you have %o tasks in list.\n", taskList.sizeOfList())).append(ui.showLine());
        storage.write(taskList);
        Ui.printString(resultSb.toString());
    }

    /**
     * Indicator for application to end.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
