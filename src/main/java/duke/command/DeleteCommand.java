package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;

/**
 * Delete command type.
 * Remove task from task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class DeleteCommand extends Command {

    private final String fullCommand;

    /**
     * Class constructor.
     * Extracts task index details from full command.
     *
     * @param fullCommand full command input by user.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Removes task with index from task arraylist.
     * Writes to file.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     * @throws DukeException unable to delete item.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] input = fullCommand.split(" ");

        StringBuilder resultSb = new StringBuilder(
                String.format("%s\n %s\n", ui.showLine(), "Noted. I've removed this task: "));
        if (input.length == 2) {
            int index = Integer.parseInt(input[1]);
            resultSb.append(String.format("\t%s\n", taskList.retrieveTask(index - 1)));
            taskList.deleteTask(Integer.parseInt(input[1]) - 1);
        } else {
            throw new DukeException("Cannot delete item!");
        }
        resultSb.append(
                String.format("Now you have %o tasks in the list\n %s", taskList.sizeOfList(), ui.showLine()));
        Ui.printString(resultSb.toString());

        storage.write(taskList);
    }

    /**
     * Returns indication for application to end.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
