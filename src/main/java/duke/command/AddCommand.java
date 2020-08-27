package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.time.LocalDate;

/**
 * Represents a command where a new task will be added to the TaskList during the usage of the Duke programme.
 */
public class AddCommand extends Command {

    Task taskToAdd;

    /**
     * Private constructor that assigns the task to be added to this AddCommand.
     * @param task the task to be assigned to this AddCommand
     */
    private AddCommand(Task task) {
        taskToAdd = task;
    }

    /**
     * Public method to construct an AddCommand to add a Todo object.
     * @param description the description of the Todo object
     * @return an AddCommand used to add a Todo object to the TaskList
     * @see Todo
     */
    public static AddCommand addTodo(String description) {
        return new AddCommand(new Todo(description));
    }

    /**
     * Public method to construct an AddCommand to add an Event object.
     * @param description the description of the Event
     * @param date the date of the Event in the form of a LocalDate object
     * @return an AddCommand used to add a Event object to the TaskList
     * @see Event
     */
    public static AddCommand addEvent(String description, LocalDate date) {
        return new AddCommand(new Event(description, date));
    }

    /**
     * Public method to construct an AddCommand to add an Deadline object.
     * @param description the description of the Deadline
     * @param date the date of the Deadline in the form of a LocalDate object
     * @return an AddCommand used to add a Deadline object to the TaskList
     * @see Deadline
     */
    public static AddCommand addDeadline(String description, LocalDate date) {
        return new AddCommand(new Deadline(description, date));
    }

    /**
     * Overridden abstract method used to add the Task stored in this AddCommand to the TaskList given.
     * After adding the Task, the task information and total number of tasks will be printed.
     * @param tasks the TaskList to add Task objects into
     * @param storage unused Storage object
     * @param ui unused Ui object
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(taskToAdd);
        System.out.println("Alrighty, I'll put it on yer tab:\n" + taskToAdd.toString() + "\n"
                + "You've got a total of " + tasks.size() + " items right now.");
    }

}
