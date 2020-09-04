package focus.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import focus.exception.FocusException;
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
        if ("todo".equals(taskType)) {
            return addToDo(input, taskList, storage);
        } else if ("deadline".equals(taskType)) {
            return addDeadline(input, taskList, storage);
        } else if ("event".equals(taskType)) {
            return addEvent(input, taskList, storage);
        }
        assert false : "All types of tasks are dealt with and program should not reach here.";
        return "";
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
            throw new FocusException("\tThe description of a todo cannot be empty!\n"
                    + "\tAn example would be:\n"
                    + "\ttodo week 3 quiz");
        }
        if (information.isBlank()) {
            throw new FocusException("\tThe description of a todo cannot be empty!\n"
                    + "\tAn example would be:\n"
                    + "\ttodo week 3 quiz");
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
            throw new FocusException("\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00");
        }
        if (information.isBlank()) {
            throw new FocusException("\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00");
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            throw new FocusException("\tPlease input the appropriate command!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00");
        }

        String description = information.substring(1, end - 1);
        String by;
        try { // user did not input date of deadline task
            by = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FocusException("\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00");
        }
        if (by.isBlank()) {
            throw new FocusException("\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00");
        }

        String formattedBy = by.replace(' ', 'T');
        LocalDateTime date;
        try { // user did not input correct format of date of deadline task
            date = LocalDateTime.parse(formattedBy);
        } catch (DateTimeParseException e) {
            throw new FocusException("\tPlease input the correct date format!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00");
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
            throw new FocusException("\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00");
        }
        if (information.isBlank()) {
            throw new FocusException("\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00");
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            throw new FocusException("\tPlease input the appropriate command!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00");
        }

        String description = information.substring(1, end - 1);
        String at;
        try { // user did not input date of event task
            at = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FocusException("\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00");
        }
        if (at.isBlank()) {
            throw new FocusException("\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00");
        }

        String formattedAt = at.replace(' ', 'T');
        LocalDateTime date;
        try { // user did not input correct format of date of event task
            date = LocalDateTime.parse(formattedAt);
        } catch (DateTimeParseException e) {
            throw new FocusException("\tPlease input the correct date format!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00");
        }
        assert !description.isEmpty() : "Description should not be blank here.";
        return taskList.addEvent(description, date, storage);
    }
}
