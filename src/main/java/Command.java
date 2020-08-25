import java.time.LocalDateTime;

/**
 * contains information from the commands
 */
public class Command {
    private String taskName;
    private LocalDateTime taskDateTime;

    /**
     * Constructor of Commands
     *
     * @param taskName
     */
    public Command(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Constructor for Deadline and Event Commands
     *
     * @param taskName
     * @param taskDateTime
     */
    public Command(String taskName, LocalDateTime taskDateTime) {
        this.taskName = taskName;
        this.taskDateTime = taskDateTime;
    }

    /**
     * getter for the name of the tasks
     *
     * @return task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * getter for the deadline/ event date and time
     *
     * @return deadline/ event date and time
     */
    public LocalDateTime getTaskDateTime() {
        return this.taskDateTime;
    }

    /**
     * performs actions based on the tasks in the tasklist
     *
     * @param taskList
     */
    public void execute(TaskList taskList) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
