import java.time.LocalDateTime;

/**
 * contains information from the commands
 */
public class Command {
    private String taskName;
    private LocalDateTime taskDateTime;
    private String duration;

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
     * Constructor for Fixed Duration Task Commands
     *
     * @param taskName
     * @param duration
     */

    public Command(String taskName, String duration) {
        this.taskName = taskName;
        this.duration = duration;
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

    public String getDuration() {
        return this.duration;
    }
    /**
     * performs actions based on the tasks in the tasklist
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
