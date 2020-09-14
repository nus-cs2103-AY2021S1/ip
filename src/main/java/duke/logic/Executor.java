package duke.logic;

import duke.Command;
import duke.DukeInputException;
import duke.Priority;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DateTimeHandler;
import duke.util.Storage;
import duke.util.TaskList;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Encapsulates the execution of user's command.
 */
public class Executor {

    private static final String ERROR_INDICATOR = "ERROR";

    // Error messages
    private static final String ERR_INDEX_OUT_OF_BOUND = "The task's number is not within the range of the list.\n";
    private static final String ERR_DELETE_MISSING_TASK_NUM = "Which task's number do you want to delete?\n";
    private static final String ERR_DONE_MISSING_TASK_NUM = "Which task's number do you want to mark as done?\n";
    private static final String ERR_TASK_MISSING_PRIORITY = "Indicate the priority of the task after \"/p\".\n";
    private static final String ERR_TASK_INVALID_PRIORITY = "The priority of a task can only be low, medium or high.\n";
    private static final String ERR_TODO_WRONG_FORMAT =
            "Use the format \"todo <description> /p <priority>\".\n";
    private static final String ERR_EVENT_WRONG_FORMAT =
            "Use the format \"event <description> /p <priority> /at <DD-MM-YYYY HHMM>\".\n";
    private static final String ERR_DEADLINE_WRONG_FORMAT =
            "Use the format \"deadline <description> /p <priority> /by <DD-MM-YYYY HHMM>\".\n";

    /** Storage for executor to write data to. */
    private final Storage storage;

    /** UI for executor to display messages. */
    private final Ui ui;

    /** Task list for executor to add/edit/delete tasks. */
    private TaskList taskList;

    /** Checks if the task's index is outside the task list's size range. */
    private final Function<Integer, Boolean> checkTaskIndex = i -> i < 0 || i >= this.taskList.getNumOfTasks();

    /**
     * Creates a new executor from a storage.
     *
     * @param storage Storage of Duke's data.
     */
    public Executor(Storage storage) {
        this.storage = storage;
        this.ui = this.storage.getUi();
        this.taskList = this.storage.getTaskList();
    }

    /**
     * Executes a command based on the user action.
     *
     * @param command Command type.
     * @param userAction String indicating the user action.
     * @return String describing the result of the execution.
     * @throws DukeInputException If user action is wrongly formatted.
     */
    public String executeCommand(Command command, String userAction) throws DukeInputException {
        switch (command) {
        case BYE:
            return this.ui.displayMessage("Bye. Hope to see you again soon!");
        case LIST:
            return this.ui.displayTaskList(this.taskList);
        case DELETE:
            return deleteTask(userAction);
        case DONE:
            return markTaskAsDone(userAction);
        case FILTER:
            return filterPriority(userAction);
        case GET:
            return displayTasksFromDate(userAction);
        case FIND:
            return findTasks(userAction);
        case TODO:
            return createTodo(userAction);
        case EVENT:
            return createEvent(userAction);
        case DEADLINE:
            return createDeadline(userAction);
        default:
            return "";
        }
    }

    /**
     * Returns a footer containing the number of tasks in the list.
     *
     * @return Footer string containing the number of tasks.
     */
    private String getNumOfTasksFooter() {
        int numOfTasks = this.taskList.getNumOfTasks();
        if (numOfTasks == 1) {
            return "Now you have " + numOfTasks + " task in the list.\n";
        } else {
            return "Now you have " + numOfTasks + " tasks in the list.\n";
        }
    }

    /**
     * Deletes a task specified by the user.
     *
     * @param indexString String indicating which task's index to be deleted.
     * @return String describing the result of deleting a task.
     * @throws DukeInputException If index is not a number String or exceeds the task list size.
     */
    private String deleteTask(String indexString) throws DukeInputException {
        try {
            int index = Integer.parseInt(indexString) - 1;

            if (this.checkTaskIndex.apply(index)) {
                throw new DukeInputException(Executor.ERR_INDEX_OUT_OF_BOUND);
            }

            Task task = this.taskList.getTask(index);
            this.taskList.deleteTask(index); // delete task from the list
            this.storage.writeToSaveFile(); // edit the data in storage
            String confirmationMessage = "Noted. I've removed this task:\n"
                    + task.toString()
                    + "\n"
                    + this.getNumOfTasksFooter();
            return this.ui.displayMessage(confirmationMessage); // print delete confirmation message
        } catch (NumberFormatException e) {
            throw new DukeInputException(Executor.ERR_DELETE_MISSING_TASK_NUM);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param indexString String indicating which task's index is done.
     * @return String describing the result of marking a task as done.
     * @throws DukeInputException If index is not a number String or exceeds the task list size.
     */
    private String markTaskAsDone(String indexString) throws DukeInputException {
        try {
            int index = Integer.parseInt(indexString) - 1;

            if (this.checkTaskIndex.apply(index)) {
                throw new DukeInputException(Executor.ERR_INDEX_OUT_OF_BOUND);
            }

            Task task = this.taskList.getTask(index);
            task.markAsDone();
            this.storage.writeToSaveFile(); // write task's data to storage
            String confirmationMessage = "Nice! I've marked this as done:\n"
                    + task.toString()
                    + "\n";
            return this.ui.displayMessage(confirmationMessage); // print mark task as done confirmation message
        } catch (NumberFormatException e) {
            throw new DukeInputException(Executor.ERR_DONE_MISSING_TASK_NUM);
        }
    }

    /**
     * Filters the list of tasks by the required priority.
     *
     * @param priorityString String describing the priority of tasks to filter by.
     * @return String describing all the tasks with a given priority.
     * @throws DukeInputException If priority String is invalid.
     */
    private String filterPriority(String priorityString) throws DukeInputException {
        Priority priority;
        switch (priorityString) {
        case "low":
            priority = Priority.LOW;
            break;
        case "medium":
            priority = Priority.MEDIUM;
            break;
        case "high":
            priority = Priority.HIGH;
            break;
        default:
            throw new DukeInputException(Executor.ERR_TASK_INVALID_PRIORITY);
        }

        StringBuilder filteredTasks = new StringBuilder();
        Stream<Task> taskStream = this.taskList.getTaskList().stream();
        AtomicInteger counter = new AtomicInteger();

        taskStream
                .filter(x -> x.getPriority() == priority)
                .forEach(x -> filteredTasks
                        .append(counter.incrementAndGet())
                        .append(". ")
                        .append(x.toString())
                        .append("\n"));

        if (filteredTasks.length() == 0) {
            return this.ui.displayMessage("You have no " + priorityString + "-priority tasks.\n");
        }

        String taskMessage = "Here are the " + priorityString + "-priority task(s):\n" + filteredTasks.toString();
        return this.ui.displayMessage(taskMessage);
    }

    /**
     * Displays the tasks with the date required by the user.
     *
     * @param dateString String describing the date of tasks required.
     * @return String describing all the tasks from a date.
     * @throws DukeInputException If date String is wrongly formatted.
     */
    private String displayTasksFromDate(String dateString) throws DukeInputException {
        String requiredDate = DateTimeHandler.parseDate(dateString);
        StringBuilder requiredTasks = new StringBuilder();
        Stream<Task> taskStream = this.taskList.getTaskList().stream();
        AtomicInteger counter = new AtomicInteger();

        taskStream
                .filter(x -> x.toString().contains(requiredDate))
                .forEach(x -> requiredTasks
                        .append(counter.incrementAndGet())
                        .append(". ")
                        .append(x.toString())
                        .append("\n"));

        if (requiredTasks.length() == 0) {
            return this.ui.displayMessage("You have no tasks from " + requiredDate + ".\n");
        }

        String taskMessage = "Here are the task(s) from " + requiredDate + ":\n" + requiredTasks.toString();
        return this.ui.displayMessage(taskMessage);
    }

    /**
     * Finds the tasks with the given keyword.
     *
     * @param keyword String describing the keyword to search for.
     * @return String describing the result of searching a keyword.
     */
    private String findTasks(String keyword) {
        StringBuilder relevantTasks = new StringBuilder();
        Stream<Task> taskStream = this.taskList.getTaskList().stream();
        AtomicInteger counter = new AtomicInteger();

        taskStream
                .filter(x -> x.getDescription().contains(keyword))
                .forEach(x -> relevantTasks
                        .append(counter.incrementAndGet())
                        .append(". ")
                        .append(x.toString())
                        .append("\n"));

        if (relevantTasks.length() == 0) {
            return this.ui.displayMessage("You have no matching tasks for the keyword: \"" + keyword + "\".\n");
        }

        String taskMessage = "Here are the matching task(s) in your list:\n" + relevantTasks.toString();
        return this.ui.displayMessage(taskMessage);
    }

    /**
     * Extracts the priority of a task from its description.
     *
     * @param description Description of the task.
     * @return Priority of the task.
     * @throws DukeInputException If the priority is missing or invalid.
     */
    private Priority extractPriority(String description) throws DukeInputException {
        if (!description.contains("/p")) {
            throw new DukeInputException(Executor.ERR_TASK_MISSING_PRIORITY);
        }

        if (description.contains("/p low")) {
            return Priority.LOW;
        } else if (description.contains("/p medium")) {
            return Priority.MEDIUM;
        } else if (description.contains("/p high")) {
            return Priority.HIGH;
        } else {
            throw new DukeInputException(Executor.ERR_TASK_INVALID_PRIORITY);
        }
    }

    /**
     * Removes the priority label from the description.
     *
     * @param priority Priority of the task.
     * @param description Description of the task.
     * @return Description of the task without the priority label.
     */
    private String removePriorityLabel(Priority priority, String description) {
        String actualDescription;
        switch (priority) {
        case LOW:
            actualDescription = description.replace(" /p low", "");
            break;
        case MEDIUM:
            actualDescription = description.replace(" /p medium", "");
            break;
        case HIGH:
            actualDescription = description.replace(" /p high", "");
            break;
        default:
            actualDescription = Executor.ERROR_INDICATOR;
            break;
        }

        if (actualDescription.contains("/p")) {
            actualDescription = Executor.ERROR_INDICATOR;
        }

        return actualDescription;
    }

    /**
     * Creates a todo which is added to the task list.
     *
     * @param description Description of the todo.
     * @return String describing the result of creating a todo.
     * @throws DukeInputException If the priority is missing or invalid or the description is wrongly formatted.
     */
    private String createTodo(String description) throws DukeInputException {
        Priority priority = this.extractPriority(description);
        String actualDescription = this.removePriorityLabel(priority, description);
        if (actualDescription.equals(Executor.ERROR_INDICATOR)) {
            throw new DukeInputException(Executor.ERR_TODO_WRONG_FORMAT);
        }
        Task task = new Todo(priority, actualDescription);
        return this.addTask(task);
    }

    /**
     * Creates an event which is added to the task list.
     *
     * @param description Description of the event.
     * @return String describing the result of creating an event.
     * @throws DukeInputException If the priority is missing or invalid or the description is wrongly formatted.
     */
    private String createEvent(String description) throws DukeInputException {
        Priority priority = this.extractPriority(description);
        String actualDescription = this.removePriorityLabel(priority, description);
        String[] arr = actualDescription.split(" /at ", 2);
        if (actualDescription.equals(Executor.ERROR_INDICATOR) || arr.length < 2 || arr[1].equals("")) {
            throw new DukeInputException(Executor.ERR_EVENT_WRONG_FORMAT);
        }
        Task task = new Event(priority, arr[0], arr[1]);
        return this.addTask(task);
    }

    /**
     * Creates a deadline which is added to the task list.
     *
     * @param description Description of the deadline.
     * @return String describing the result of creating a deadline.
     * @throws DukeInputException If the priority is missing or invalid or the description is wrongly formatted.
     */
    private String createDeadline(String description) throws DukeInputException {
        Priority priority = this.extractPriority(description);
        String actualDescription = this.removePriorityLabel(priority, description);
        String[] arr = actualDescription.split(" /by ", 2);
        if (actualDescription.equals(Executor.ERROR_INDICATOR) || arr.length < 2 || arr[1].equals("")) {
            throw new DukeInputException(Executor.ERR_DEADLINE_WRONG_FORMAT);
        }
        Task task = new Deadline(priority, arr[0], arr[1]);
        return this.addTask(task);
    }

    /**
     * Adds a task to the task list.
     * Either a todo, event or deadline task.
     *
     * @param task Task to be added to the task list.
     * @return String describing the result of adding a task.
     */
    private String addTask(Task task) {
        this.taskList.addTask(task);
        this.storage.writeToSaveFile(); // write task's data to storage
        String confirmationMessage = "Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + getNumOfTasksFooter();
        return this.ui.displayMessage(confirmationMessage); // print create task confirmation message
    }
}
