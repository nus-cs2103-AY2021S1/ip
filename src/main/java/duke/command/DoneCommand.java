package duke.command;

import java.io.IOException;

import duke.core.MessageType;
import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.handle.TaskNotFoundException;

/**
 * The DoneCommand class represents a command that marks a task as completed in the task list.
 */
public class DoneCommand extends Command {
    private int count;

    /**
     * Takes in the count of the task to be completed and returns a complete command.
     *
     * @param count The count of the task.
     */
    public DoneCommand(int count) {
        this.count = count;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and mark a task to be
     * completed in the task list and updates the local record using storage.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @return The result of the execution of the command.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled.
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {

        if (!taskList.has(count - 1)) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task\n"
                    + "Type list to see the list of tasks");
        } else {
            taskList.markAsCompleted(count - 1);
            //ui.showDone(taskList.getTask(count - 1), count);
            storage.writeRecord(taskList);

            return new Result(ui.getDoneMessage(taskList.getTask(count - 1), count),
                    this.isContinuing(),
                    MessageType.COMMAND_FOUND_MESSAGE);
        }
    }
}
