package duke;

import java.time.LocalDate;
import java.util.Objects;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Encapsulates a single command for the Duke program.
 */
public class Command {
    protected TaskType taskType;
    protected Integer index;
    protected String description;
    protected LocalDate date;
    protected Integer hours;

    /**
     * Creates an invalid command.
     */
    public Command() {
        taskType = null;
    }

    /**
     * Creates a Command representing a task of either LIST/BYE.
     *
     * @param taskType the type of task
     */
    public Command(TaskType taskType) {
        assert taskType == TaskType.LIST || taskType == TaskType.BYE : "taskType not supported for this constructor";
        this.taskType = taskType;
        index = null;
        description = null;
        date = null;
        hours = null;
    }

    /**
     * Creates a Command representing a task of either DELETE or DONE.
     *
     * @param taskType the type of task
     * @param index    index of TaskList the action is to be applied to
     */
    public Command(TaskType taskType, Integer index) {
        assert taskType == TaskType.DELETE || taskType == TaskType.DONE : "taskType not supported for this constructor";
        this.taskType = taskType;
        this.index = index;
        description = null;
        date = null;
        hours = null;
    }

    /**
     * Creates a Command representing a task of TODO.
     *
     * @param taskType    the type of task
     * @param description the description of the task
     */
    public Command(TaskType taskType, String description) {
        assert taskType == TaskType.FIND : "taskType not supported for this constructor";
        this.taskType = taskType;
        index = null;
        this.description = description;
        date = null;
        hours = null;
    }

    /**
     * Creates a Command representing a task of either a DEADLINE or EVENT.
     *
     * @param taskType    the type of task
     * @param description the description of the task
     * @param hours       the duration of the task
     */
    public Command(TaskType taskType, String description, Integer hours) {
        assert taskType == TaskType.TODO : "taskType not supported for this constructor";
        this.taskType = taskType;
        index = null;
        this.description = description;
        this.hours = hours;
    }

    /**
     * Creates a Command representing a task of either a DEADLINE or EVENT.
     *
     * @param taskType    the type of task
     * @param description the description of the task
     * @param date        the date of the task
     */
    public Command(TaskType taskType, String description, LocalDate date) {
        assert taskType == TaskType.DEADLINE || taskType == TaskType.EVENT
            : "taskType not supported for this constructor";
        this.taskType = taskType;
        index = null;
        this.description = description;
        this.date = date;
        hours = null;
    }

    /**
     * Returns the TaskType of the Command.
     *
     * @return the TaskType of the command
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns the respective date of the Command.
     *
     * @return the date of the command
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns true if the Command object is valid.
     *
     * @return true if the command is valid
     */
    public boolean isValid() {
        return taskType != null;
    }

    private static String addToList(TaskList taskList, Task task) {
        taskList.add(task);
        return "Sure, I've added this task to your list:\n " + task
            + "\nYou now have " + taskList.size() + " task(s) in the list!";
    }

    /**
     * Executes the Command.
     *
     * @param list the TaskList this command is to be applied to
     */
    public String execute(TaskList list) {
        if (!isValid()) {
            return null;
        }

        String print = "";
        try {
            switch (taskType) {
            case LIST:
                print = "Here are the tasks in your list:";
                if (list.isEmpty()) {
                    print += "\nYour list is empty. How about adding some tasks?";
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        print += "\n " + (i + 1) + ": " + list.get(i);
                    }
                }
                break;
            case DELETE:
                Task deleted = list.remove(index - 1);
                print = "Sure, I've deleted this task from your list:\n " + deleted
                    + "\nYou now have " + list.size() + " task(s) in the list.";
                break;
            case DONE:
                Task doneTask = list.markTaskAsDone(index - 1);
                print = "Great! I've marked this task as done:\n " + doneTask;
                break;
            case TODO:
                print = addToList(list, new Todo(description, hours));
                break;
            case DEADLINE:
                print = addToList(list, new Deadline(description, date));
                break;
            case EVENT:
                print = addToList(list, new Event(description, date));
                break;
            case FIND:
                TaskList foundList = list.find(description);
                print = "Here are your search results:";
                if (foundList.isEmpty()) {
                    print += "\nWe didn't find any tasks relevant to your search term.";
                } else {
                    for (int i = 0; i < foundList.size(); i++) {
                        print += "\n " + (i + 1) + ": " + foundList.get(i);
                    }
                }
                break;
            case BYE:
                break;
            default:
                throw new IllegalArgumentException(taskType + " is not supported as a TaskType.\n"
                    + "Contact the developer if you see this message.");
            }
        } catch (IndexOutOfBoundsException e) {
            print = "The index you provided was out of bounds.\nRun list to see your list of tasks.";
        }
        return print;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return taskType == command.taskType
            && Objects.equals(index, command.index)
            && Objects.equals(description, command.description)
            && Objects.equals(date, command.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskType, index, description, date);
    }
}
