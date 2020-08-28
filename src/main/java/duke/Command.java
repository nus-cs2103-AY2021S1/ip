package duke;

public interface Command {
    void execute(TaskList tasks);

    default boolean setIsFinished() {
        return false;
    }
}
