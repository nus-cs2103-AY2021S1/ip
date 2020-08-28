package duke.task;

public interface TaskList {
    void add(Task t);

    Task get(int i);

    Task remove(int i);

    int size();

    void update(int index);
}
