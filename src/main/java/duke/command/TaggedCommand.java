package duke.command;

import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.response.Response;
import duke.task.Task;

/**
 * Represents a tag command.
 */
public class TaggedCommand extends Command {
    private final List<String> tags;

    public TaggedCommand(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Executes the command, listing all tasks with the given tags.
     *
     * @param taskList A TaskList instance.
     * @param storage A Storage instance.
     * @throws DukeException if there are no tasks with the given tags.
     */
    @Override
    public Response execute(TaskList taskList, Storage storage) throws DukeException {
        List<Task> taggedTasks = taskList.getTaggedTasks(this.tags);

        if (taggedTasks.size() == 0) {
            throw new DukeException("There are no tasks in your list tagged with the given tags.");
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list tagged with the given tags:\n");

        for (Task task : taggedTasks) {
            output.append(taskList.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        return new Response(output.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaggedCommand) {
            return this.tags.equals(((TaggedCommand) obj).tags);
        }

        return false;
    }
}
