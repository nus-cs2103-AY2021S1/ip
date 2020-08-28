package duke;

public interface Command {
    public void execute(TaskList tasks);
    public default boolean setIsFinished() {
        return false;
    };
}
