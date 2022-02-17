package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;

/**
 * Done command type. Mark task to done.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class DoneCommand extends Command {

    private final String fullCommand;

    /**
     * Class constructor.
     * Extracts task index details from full command.
     *
     * @param fullCommand full command input by user.
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Retrieves task to be mark done and change isDone to true.
     * Writes to file.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     * @throws DukeException unable to find task item.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] input = fullCommand.split(" ");

        Task task;
        if (input.length == 2) {
            task = taskList.retrieveTask(Integer.parseInt(input[1]) - 1);
            task.markAsDone();
        } else {
            throw new DukeException("Cannot mark item done!");
        }
        Ui.printString(String.format("%s\n %s\n", ui.showLine(), "Nice! I've marked this task as done:")
                + (String.format("%s\n %s", task, ui.showLine())));

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
