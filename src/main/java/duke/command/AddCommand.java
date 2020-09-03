package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import duke.ui.Ui;

/**
 * Represents an add task command.
 */
public class AddCommand implements Command {
    /** key decides the type of task to create */
    private String key;
    private String taskDescription;
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates an AddCommand instance with no date/time for todos.
     *
     * @param key Todo command
     * @param taskDescription Task description.
     */
    public AddCommand(String key, String taskDescription) {
        this.key = key;
        this.taskDescription = taskDescription;
        this.date = null;
        this.time = null;
    }

    /**
     * Creates an AddCommand instance with date and time for events and deadlines.
     *
     * @param key Deadline or event command.
     * @param taskDescription Task description.
     * @param date Date of task.
     * @param time Time of task.
     */
    public AddCommand(String key, String taskDescription, LocalDate date, LocalTime time) {
        this.key = key;
        this.taskDescription = taskDescription;
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a Task instance with the task details and adds it to the user's task list.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        if (CommandKey.equalsCommandKey(key, CommandKey.TODO)) {
            Task task = new ToDo(taskDescription);
            tasks.add(task);
            return ui.displayAddTaskSuccess(task, tasks.size());
        } else if (CommandKey.equalsCommandKey(key, CommandKey.DEADLINE)) {
            Task task = new Deadline(taskDescription, date, time);
            tasks.add(task);
            return ui.displayAddTaskSuccess(task, tasks.size());
        } else {
            Task task = new Event(taskDescription, date, time);
            tasks.add(task);
            return ui.displayAddTaskSuccess(task, tasks.size());
        }
    }

    /**
     * Tells Duke to continue running.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
