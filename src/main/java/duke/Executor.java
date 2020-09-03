package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DateTimeHandler;

/**
 * Encapsulates the execution of user's command.
 */
public class Executor {

    /** Storage for executor to write data to. */
    private final Storage storage;

    /** UI for executor to display messages. */
    private final Ui ui;

    /** Task list for executor to add/edit/delete tasks. */
    private final TaskList taskList;

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

            // Check if index is within the task list size
            if (index >= 0 && index < this.taskList.getNumOfTasks()) {
                Task task = this.taskList.getTask(index);
                this.taskList.deleteTask(index); // delete task from the list
                this.storage.writeToSaveFile(); // edit the data in storage
                String confirmationMessage = "Noted. I've removed this task:\n"
                        + task.toString()
                        + "\n"
                        + this.getNumOfTasksFooter();
                return this.ui.displayMessage(confirmationMessage); // print delete confirmation message
            } else {
                throw new DukeInputException("The index is not within the range of the list.\n");
            }
        } catch (NumberFormatException e) {
            throw new DukeInputException("Include the index of the task to be deleted.\n");
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

            // Check if index is within the task list size
            if (index >= 0 && index < this.taskList.getNumOfTasks()) {
                Task task = this.taskList.getTask(index);
                task.markAsDone();
                this.storage.writeToSaveFile(); // write task's data to storage
                String confirmationMessage = "Nice! I've marked this as done:\n"
                        + task.toString()
                        + "\n";
                return this.ui.displayMessage(confirmationMessage); // print mark task as done confirmation message
            } else {
                throw new DukeInputException("The index is not within the range of the list.\n");
            }
        } catch (NumberFormatException e) {
            throw new DukeInputException("Include the index of the task done.\n");
        }
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
        boolean hasRequiredTasks = false;
        StringBuilder requiredTasks = new StringBuilder();

        int number = 0;
        for (int i = 0; i < this.taskList.getNumOfTasks(); i++) {
            String taskString = this.taskList.getTask(i).toString();
            if (taskString.contains(requiredDate)) {
                hasRequiredTasks = true;
                number++;
                requiredTasks.append(number).append(". ").append(taskString).append("\n");
            }
        }

        if (hasRequiredTasks) {
            String taskMessage = "Here are the task(s) from " + requiredDate + ":\n" + requiredTasks;
            return this.ui.displayMessage(taskMessage);
        } else {
            return this.ui.displayMessage("You have no tasks from " + requiredDate + ".");
        }
    }

    /**
     * Finds the tasks with the given keyword.
     *
     * @param keyword String describing the keyword to search for.
     * @return String describing the result of searching a keyword.
     */
    private String findTasks(String keyword) {
        boolean hasRelevantTasks = false;
        StringBuilder relevantTasks = new StringBuilder();

        // Add tasks with keyword contained in the description
        int number = 0;
        for (int i = 0; i < this.taskList.getNumOfTasks(); i++) {
            Task task = this.taskList.getTask(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                hasRelevantTasks = true;
                number++;
                relevantTasks.append(number).append(". ").append(task.toString()).append("\n");
            }
        }

        // Print message according to whether any relevant tasks have been found
        if (hasRelevantTasks) {
            String taskMessage = "Here are the matching task(s) in your list:\n" + relevantTasks;
            return this.ui.displayMessage(taskMessage);
        } else {
            return this.ui.displayMessage("You have no matching tasks for the keyword: \"" + keyword + "\".\n");
        }
    }

    /**
     * Creates a todo which is added to the task list.
     *
     * @param description Description of the todo.
     * @return String describing the result of creating a todo.
     */
    private String createTodo(String description) {
        Task task = new Todo(description);
        return this.addTask(task);
    }

    /**
     * Creates an event which is added to the task list.
     *
     * @param description Description of the event.
     * @return String describing the result of creating an event.
     * @throws DukeInputException If the description is wrongly formatted.
     */
    private String createEvent(String description) throws DukeInputException {
        String[] arr = description.split(" /at ", 2);
        if (arr.length < 2 || arr[1].equals("")) {
            throw new DukeInputException("Include the date and time of the event after \"/at\".\n");
        }
        Task task = new Event(arr[0], arr[1]);
        return this.addTask(task);
    }

    /**
     * Creates a deadline which is added to the task list.
     *
     * @param description Description of the deadline.
     * @return String describing the result of creating a deadline.
     * @throws DukeInputException If the description is wrongly formatted.
     */
    private String createDeadline(String description) throws DukeInputException {
        String[] arr = description.split(" /by ", 2);
        if (arr.length < 2 || arr[1].equals("")) {
            throw new DukeInputException("Include the date and time of the deadline after \"/by\".\n");
        }
        Task task = new Deadline(arr[0], arr[1]);
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
