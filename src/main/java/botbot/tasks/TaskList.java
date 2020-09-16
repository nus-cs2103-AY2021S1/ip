package botbot.tasks;

import static botbot.ui.Ui.INDENT;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import botbot.tasks.Deadline;
import botbot.tasks.Event;
import botbot.tasks.Task;
import botbot.tasks.Todo;

/**
 * Represents the Botbot task list.
 */
public class TaskList extends LinkedList<Task> {
    private static final String ERROR_MESSAGE_ILLEGAL_ARG = "you cannot set a %s for a%s!";
    private static final String ERROR_MESSAGE_ILLEGAL_ARG_DEADLINE = String.format(ERROR_MESSAGE_ILLEGAL_ARG,
            "time", " deadline");
    private static final String ERROR_MESSAGE_ILLEGAL_ARG_EVENT = String.format(ERROR_MESSAGE_ILLEGAL_ARG,
            "deadline", "n event");
    private static final String ERROR_MESSAGE_ILLEGAL_ARG_TODO_AT = String.format(ERROR_MESSAGE_ILLEGAL_ARG,
            "time", " todo");
    private static final String ERROR_MESSAGE_ILLEGAL_ARG_TODO_BY = String.format(ERROR_MESSAGE_ILLEGAL_ARG,
            "deadline", " todo");
    
    /**
     * Creates a task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Creates a task list.
     *
     * @param tasks Tasks in task list.
     */
    public TaskList(Collection<Task> tasks) {
        super(tasks);
    }

    /**
     * Deletes a task in the task list.
     *
     * @param id ID of task to be deleted.
     */
    public void delete(int id) {
        assert size() > 0 && id <= size() : "Invalid ID to delete";
        remove(id);
    }

    /**
     * Edits a task in the task list.
     *
     * @param id ID of task to be edited.
     * @param description Description to be edited to.
     * @param at Time to be edited to.
     * @param by Deadline to be edited to.
     * @throws IllegalArgumentException If 'at' is provided for a deadline or to-do, or if 'by' is 
     *         provided for an event or to-do.
     */
    public void edit(int id, Optional<String> description, Optional<LocalDateTime> at,
            Optional<LocalDateTime> by) throws IllegalArgumentException {
        assert size() > 0 && id <= size() : "Invalid ID to edit";
        Task task = get(id);
        if (task instanceof Todo) {
            at.ifPresent(x -> {
                throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_ARG_TODO_AT);
            });
            by.ifPresent(x -> {
                throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_ARG_TODO_BY);
            });
        } else if (task instanceof Deadline) {
            at.ifPresent(x -> {
                throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_ARG_DEADLINE);
            });
            by.ifPresent(task::setBy);
        } else if (task instanceof Event) {
            by.ifPresent(x -> {
                throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_ARG_EVENT);
            });
            at.ifPresent(task::setAt);
        }
        description.ifPresent(task::setDescription);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(String.format(INDENT + "%s. %s\n", i + 1, this.get(i)));
        }
        return result.toString();
    }
}
