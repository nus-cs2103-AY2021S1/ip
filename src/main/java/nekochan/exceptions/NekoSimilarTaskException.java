package nekochan.exceptions;

import java.util.List;

import nekochan.task.Task;

public class NekoSimilarTaskException extends NekoException {

    private final List<Task> similarTasks;

    /**
     * Constructs a new NekoSimilarTaskException with the specified {@code message} and list of {@code similarTasks}.
     *
     * @param message the detail message.
     * @param similarTasks the list of {@link Task} that were found to be similar.
     */
    public NekoSimilarTaskException(String message, List<Task> similarTasks) {
        super(message);
        this.similarTasks = similarTasks;
    }

    public List<Task> getSimilarTask() {
        return similarTasks;
    }
}
