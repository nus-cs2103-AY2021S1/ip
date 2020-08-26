package duke.task;

import java.util.function.Consumer;

public interface TaskList {
    void add(Task t);

    Task get(int i);

    Task remove(int i);

    int size();

    void forEach(Consumer<Task> action);
}
