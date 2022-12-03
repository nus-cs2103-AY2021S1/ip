package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;



/**
 * A command to add different types of tasks.
 */
public class AddCommand extends Command {

    /** Stores the type of the task to be added. */
    private String taskType;

    /** Stores the description of the task to be added. */
    private String description;

    /** Stores the time of the task to be added. */
    private LocalTime localTime = null;

    /** Stores the date of the task to be added. */
    private LocalDate localDate = null;

    /**
     * Creates an add command with appropriate task info.
     * @param taskType Type of task to be added.
     * @param description Task description.
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Overloaded constructor specifically for deadline and event tasks.
     * @param taskType Type of task to be added.
     * @param description Task description.
     * @param localDate Task date.
     * @param localTime Task time.
     */
    public AddCommand(String taskType, String description, LocalDate localDate, LocalTime localTime) {
        this.taskType = taskType;
        this.description = description;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    /**
     * Executes the command to perform its appropriate action.
     * Adds different types of tasks depending on the task type.
     * @param tasks Duke task list.
     * @param ui Ui object to print user messages.
     * @param storage Storage object to load and save tasks to data file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response = "";
        switch (taskType) {
        case "todo" :
            response = tasks.addTodo(ui, this.description);
            storage.save(tasks.convertToFile());
            return response;
            //Fallthrough
        case "event" :
            response = tasks.addEvent(ui, this.description, this.localDate, this.localTime);
            storage.save(tasks.convertToFile());
            return response;
            //Fallthrough
        case "deadline" :
            response = tasks.addDeadline(ui, this.description, this.localDate, this.localTime);
            storage.save(tasks.convertToFile());
            return response;
            //Fallthrough
        default:
            return "Error in AddCommand execute method";
            //Fallthrough
        }
    }
}
