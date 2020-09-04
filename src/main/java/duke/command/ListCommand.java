package duke.command;

import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;

/**
 * List command type.
 * Print all the list in the task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class ListCommand extends Command {

    /**
     * Print all the task in the arraylist.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder resultSb = new StringBuilder(
                String.format("%s\n %s\n", ui.showLine(), ("Here are the tasks in " + "your list: ")));
        for (int i = 0; i < taskList.sizeOfList(); i++) {
            resultSb.append(String.format("%d.%s\n", i + 1, taskList.retrieveTask(i)));
        }
        resultSb.append(ui.showLine());
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
