package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.task.TaskPriority;

public class Argument {
    private final String description;
    private final TaskPriority priority;
    private final List<String> tags;

    /**
     * The Argument constructor.
     *
     * @param description The description from the arguments.
     * @param priority THe priority from the arguments.
     * @param tags The list of tags from the arguments.
     */
    public Argument(String description, TaskPriority priority, List<String> tags) {
        this.description = description;
        this.priority = priority;
        this.tags = new ArrayList<>(tags);
    }

    public String getDescription() {
        return this.description;
    }

    public TaskPriority getPriority() {
        return this.priority;
    }

    public List<String> getTags() {
        return this.tags;
    }
}
