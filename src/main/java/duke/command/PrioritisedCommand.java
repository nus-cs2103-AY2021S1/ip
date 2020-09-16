package duke.command;

import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskPriority;

/**
 * Represents a priority command.
 */
public class PrioritisedCommand extends Command {
    private final TaskPriority priority;

    public PrioritisedCommand(TaskPriority priority) {
        this.priority = priority;
    }

    /**
     * Executes the command, listing all tasks with the given priority.
     *
     * @param taskList A TaskList instance.
     * @param storage A Storage instance.
     * @throws DukeException if there are no tasks with the given priority.
     */
    @Override
    public Response execute(TaskList taskList, Storage storage) throws DukeException {
        List<Task> prioritisedTasks = taskList.getPrioritisedTasks(this.priority);

        if (prioritisedTasks.size() == 0) {
            throw new DukeException("There are no tasks in your list with " + this.priority + " priority.");
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list with "
                + this.priority + " priority.\n");

        for (Task task : prioritisedTasks) {
            output.append(taskList.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        return new Response(output.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PrioritisedCommand) {
            return this.priority.equals(((PrioritisedCommand) obj).priority);
        }

        return false;
    }
}
