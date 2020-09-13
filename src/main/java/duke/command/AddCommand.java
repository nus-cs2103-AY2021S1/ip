package duke.command;

import duke.DukeException;
import duke.History;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Command when user adds a new task, which can be a <code>ToDo</code>, <code>Event</code>, or <code>Deadline</code>.
 */
public class AddCommand extends Command {
    /** The specific type of task to be added */
    private final Commands c;
    private final String userInput;

    /**
     * Constructor to create an add command.
     *
     * @param userInput command give from user via command line.
     * @param c type of command which is an <code>ENUM</code>
     */
    public AddCommand(String userInput, Commands c) {
        this.userInput = userInput;
        this.c = c;
    }

    /**
     * Determines whether a <Code>ToDo</Code>, <Code>Event</Code>, or <Code>Deadline</Code> task will be
     * added.
     * <p>
     * This method parses the user input taken in, and determins which type of task will be added to
     * The database depending on the command type given.
     *
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     * @param history the state of all changes made to Duke's TaskList.
     * @return string representation of Duke's response to the command.
     * @throws DukeException when the type of task being added is unknown.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, History history) throws DukeException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "taskList cannot be null";
        switch (c) {
        case TODO:
            history.addToHistory(taskList);
            return addToDoTask(this.userInput, taskList, storage);
        case EVENT:
            history.addToHistory(taskList);
            return addEventTask(this.userInput, taskList, storage);
        case DEADLINE:
            history.addToHistory(taskList);
            return addDeadlineTask(this.userInput, taskList, storage);
        default:
            throw new DukeException("I don't recognize the type of task you are trying to add");
        }

    }

    private static boolean checkForEmptyDescription(String userInput) {
        return userInput.length() <= 4;
    }

    private String addToDoTask(String userInput, TaskList taskList, Storage storage) throws DukeException {
        if (checkForEmptyDescription(userInput)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        String taskDescription = userInput.substring(5);
        ToDo newToDoItem = new ToDo(taskDescription);
        return addItem(newToDoItem, taskList, storage); // Add to taskList
    }

    private String addEventTask(String userInput, TaskList taskList, Storage storage) throws DukeException {
        try {
            String taskDescription = userInput.substring(6, userInput.indexOf("/at") - 1);
            String eventDateTime = userInput.substring(userInput.indexOf("/at") + 4);
            Event newEventItem = new Event(taskDescription, eventDateTime);
            return addItem(newEventItem, taskList, storage);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please refer to help instructions on how to add event task");
        }
    }

    private String addDeadlineTask(String userInput, TaskList taskList, Storage storage) throws DukeException {
        try {
            String taskDescription = userInput.substring(9, userInput.indexOf("/by") - 1);
            String deadlineBy = userInput.substring(userInput.indexOf("/by") + 4);
            Deadline newDeadlineItem = new Deadline(taskDescription, deadlineBy);
            return addItem(newDeadlineItem, taskList, storage);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please refer to help instructions on how to add deadline task");
        }
    }

    /**
     * Adds a newly added task to Duke's <code>TaskList</code>, then stores the newly added task to Duke's
     * <code>Storage</code>.
     *
     * @param newTask the new task that will be added.
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     */
    public String addItem(Task newTask, TaskList taskList, Storage storage) {
        assert newTask != null : "Task input cannot be null";
        taskList.add(newTask);
        storage.createTask(newTask); // Add to storage database
        return "Got it. I've added this task:\n   " + newTask.toString() + taskList.printNumTasks();

    }
}
