package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.response.Response;
import duke.task.Task;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {
    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the command, marking a task as done in the given TaskList.
     *
     * @param taskList A TaskList instance.
     * @param storage A Storage instance.
     * @throws DukeException if the task cannot be found.
     */
    @Override
    public Response execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.doTask(this.taskNo);
        Response response = new Response("Nice! I've marked this task as done:\n  " + task);
        storage.save(taskList.getTasks());

        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoneCommand) {
            return this.taskNo == ((DoneCommand) obj).taskNo;
        }

        return false;
    }
}
