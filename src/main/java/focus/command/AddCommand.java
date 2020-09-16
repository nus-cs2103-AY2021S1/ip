package focus.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import focus.exception.FocusException;
import focus.exception.InvalidTaskCommandException;
import focus.exception.InvalidTaskDateException;
import focus.exception.InvalidTaskDescriptionException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the AddCommand to add tasks into task list. */
public class AddCommand extends Command {
    /** Represents the type of task. */
    private final String taskType;

    /**
     * Creates AddCommand with the given task type.
     *
     * @param taskType Type of task (to-do, deadline or event).
     */
    public AddCommand(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns false since AddCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes AddCommand to add To-Do, Deadline or Event.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of added task.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        switch (taskType) {
        case "todo":
            return addToDo(input, taskList, storage);
        case "deadline":
            return addDeadline(input, taskList, storage);
        case "event":
            return addEvent(input, taskList, storage);
        default:
            assert false : "All types of tasks are dealt with and program should not reach here.";
            return "";
        }
    }

    /**
     * Adds To-Do tasks into task list.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of added To-Do.
     * @throws FocusException If input does not meet criteria.
     */
    private String addToDo(String input, TaskList taskList, Storage storage) throws FocusException {
        assert !input.isEmpty() : "Input should not be blank here.";
        String information;
        try { // user did not input description of to-do task
            information = input.split("todo")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
        if (information.isBlank()) {
            throw new InvalidTaskDescriptionException();
        }
        String description = information.substring(1);
        assert !description.isEmpty() : "Description should not be blank here.";
        return taskList.addToDo(description, storage);
    }

    /**
     * Adds Deadline tasks into task list.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of added Deadline.
     * @throws FocusException If input does not meet criteria.
     */
    private String addDeadline(String input, TaskList taskList, Storage storage) throws FocusException {
        assert !input.isEmpty() : "Input should not be blank here.";
        String information;
        try { // user did not input description of deadline task
            information = input.split("deadline")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
        if (information.isBlank()) {
            throw new InvalidTaskDescriptionException();
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            throw new InvalidTaskCommandException();
        }

        String description = information.substring(1, end - 1);
        String by;
        try { // user did not input date of deadline task
            by = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidTaskDateException();
        }
        if (by.isBlank()) {
            throw new InvalidTaskDateException();
        }

        LocalDateTime date;
        try { // user did not input correct format of date of deadline task
            String formattedBy = by.replace(' ', 'T');
            date = LocalDateTime.parse(formattedBy);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }
        assert !description.isEmpty() : "Description should not be blank here.";
        return taskList.addDeadline(description, date, storage);
    }

    /**
     * Adds Event tasks into task list.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of added Event.
     * @throws FocusException If input does not meet criteria.
     */
    private String addEvent(String input, TaskList taskList, Storage storage) throws FocusException {
        assert !input.isEmpty() : "Input should not be blank here.";
        String information;
        try { // user did not input description of event task
            information = input.split("event")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
        if (information.isBlank()) {
            throw new InvalidTaskDescriptionException();
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            throw new InvalidTaskCommandException();
        }

        String description = information.substring(1, end - 1);
        String at;
        try { // user did not input date of event task
            at = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidTaskDateException();
        }
        if (at.isBlank()) {
            throw new InvalidTaskDateException();
        }

        LocalDateTime date;
        try { // user did not input correct format of date of event task
            String formattedAt = at.replace(' ', 'T');
            date = LocalDateTime.parse(formattedAt);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }
        assert !description.isEmpty() : "Description should not be blank here.";
        return taskList.addEvent(description, date, storage);
    }
}
