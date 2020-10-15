package duke;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a command to ask tasks.
 */
public class AddCommand extends Command {
    private TaskType type;
    private String description;
    private String time;

    /**
     * Constructs a command to add a todo task.
     * 
     * @param type Type of the task to be added.
     * @param description Description of the task to be added.
     */
    public AddCommand(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.time = "";
    }

    /**
     * Constructs a command to add a deadline or event task.
     * 
     * @param type Type of the task to be added.
     * @param description Description of the task to be added.
     * @param time Timing of the task to be added.
     */
    public AddCommand(TaskType type, String description, String time) {
        this.type = type;
        this.description = description;
        this.time = time;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        Task newTask = null;
        try {
            switch (type) {
            case TODO:
                newTask = new ToDo(description);
                break;
            case DEADLINE:
                LocalDate by = LocalDate.parse(time);
                newTask = new Deadline(description, by);
                break;
            case EVENT:
                LocalDate at = LocalDate.parse(time);
                newTask = new Event(description, at);
                break;
            default:
                throw new DukeException("I don't get what you're saying :(");
            }
        } catch (DateTimeException | DukeException error) {
            System.out.println("Please provide valid date and time in the following format YYYY-MM-DD");
        }
        assert newTask != null : "Task cannot be null";
        taskList.addTask(newTask);
        store.writeFile(taskList);
        return ui.showAddition(newTask, taskList);
    }
}
