package nekochan.exceptions;

import java.util.List;

import nekochan.model.task.Task;
import nekochan.model.task.TaskList;

public class NekoSimilarTaskException extends NekoException {

    private final List<Task> similarTasks;
    private final TaskList nextState;

    /**
     * Constructs a new NekoSimilarTaskException with the specified {@code message} and list of {@code similarTasks}.
     *
     * @param message the detail message.
     * @param similarTasks the list of {@link Task} that were found to be similar.
     * @param nextState the updated state of the {@link TaskList}.
     */
    public NekoSimilarTaskException(String message, List<Task> similarTasks, TaskList nextState) {
        super(message);
        this.similarTasks = similarTasks;
        this.nextState = nextState;
    }

    public List<Task> getSimilarTask() {
        return similarTasks;
    }

    public TaskList getNextState() {
        return nextState;
    }
}
