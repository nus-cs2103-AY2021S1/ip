package duke.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskPriority;
import duke.task.TaskType;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private final TaskType type;
    private final String description;
    private final LocalDateTime dateTime;
    private final TaskPriority priority;
    private final List<String> tags;

    public AddCommand(TaskType type, String description) {
        this(type, description, TaskPriority.NONE, Collections.emptyList());
    }

    public AddCommand(TaskType type, String description, TaskPriority priority, List<String> tags) {
        this(type, description, null, priority, tags);
    }

    /**
     * The add command constructor.
     *
     * @param type The command type.
     * @param description The description.
     * @param dateTime The date/time if applicable.
     */
    public AddCommand(TaskType type, String description, LocalDateTime dateTime) {
        this(type, description, dateTime, TaskPriority.NONE, Collections.emptyList());
    }

    public AddCommand(TaskType type, String description, LocalDateTime dateTime, TaskPriority priority, List<String> tags) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.priority = priority;
        this.tags = new ArrayList<>(tags);
    }

    /**
     * Executes the command, adding a task to the given TaskList.
     *
     * @param taskList A TaskList instance.
     * @param storage A Storage instance.
     * @throws DukeException if the task cannot be added.
     */
    @Override
    public Response execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.addTask(this.type, this.description, this.dateTime, this.priority, this.tags);
        Response response = new Response("Got it. I've added this task:\n  "
                + task + "\n"
                + "Now you have " + taskList.getTasks().size()
                + (taskList.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
        storage.save(taskList.getTasks());

        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            return this.type.equals(((AddCommand) obj).type)
                    && this.description.equals(((AddCommand) obj).description)
                    && (this.dateTime == null || this.dateTime.equals(((AddCommand) obj).dateTime));
        }

        return false;
    }
}
