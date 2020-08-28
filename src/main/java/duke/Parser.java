package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Handles changing user input and making sense of it, e.g. tasks.
 */
public class Parser {

    /**
     * Processes the input given by user into a recognizable command.
     *
     * @param input User input.
     * @param handler Task list.
     * @return Command to be executed.
     * @throws DukeException if input given is invalid or unrecognized.
     */
    public static Command parse(String input, taskListHandler handler) throws DukeException {
        String firstWord = input.split(" ")[0];
        switch (firstWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            Task currentTask = Parser.parseModifyTaskCommand(input, handler);
            return new DoneCommand(currentTask);
        case "delete":
            Task delTask = Parser.parseModifyTaskCommand(input, handler);
            return new DeleteCommand(delTask);
        case "todo":
            Task newToDo = Parser.parseNewTaskCommand(input, Task.taskType.TODO);
            return new TodoCommand(newToDo);
        case "deadline":
            Task newDeadline = Parser.parseNewTaskCommand(input, Task.taskType.DEADLINE);
            return new DeadlineCommand(newDeadline);
        case "event":
            Task newEvent = Parser.parseNewTaskCommand(input, Task.taskType.EVENT);
            return new EventCommand(newEvent);
        case "clear":
            return new ClearCommand();
        case "find":
            return new FindCommand(input);
        default:
            return new InvalidCommand(input);
        }
    }

    /**
     * Processes user input that modifies a task in the task list.
     *
     * @param input User input.
     * @param handler Task list.
     * @return Task in the task list to be modified.
     * @throws DukeException if commands have too many arguments, invalid number or string is given.
     */
    public static Task parseModifyTaskCommand(String input, taskListHandler handler) throws DukeException {
        String[] stringArr = input.split(" ");
        // DONE OR DELETE
        String lowerCaseOperation = stringArr[0].toLowerCase();
        if (stringArr.length != 2 ) {
            // if multiple tasks are given as arguments
            throw new DukeException("\u2639 Oops, too many task numbers entered after "
                + lowerCaseOperation + "!");
        }
        try {
            // Finding the actual task
            int indexOfTask = Integer.parseInt(stringArr[1]) - 1;
            return handler.getTasks().get(indexOfTask);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("\u2639 Oops, " + '"' + stringArr[1] + '"'
                + " is not a valid task number for " + lowerCaseOperation + "!");
        } catch (NumberFormatException e){
            throw new DukeException("\u2639 Oops, " + '"' + stringArr[1] + '"' + " is not a number!");
        }
    }

    /**
     * Processes user input that adds a task to the task list.
     *
     * @param input User input.
     * @param tasktype Type of task to be added.
     * @return New task to be added.
     * @throws DukeException if task description is empty or invalid command format used.
     */
    public static Task parseNewTaskCommand(String input, Task.taskType tasktype) throws DukeException {
        // Sorts the input into a task with or without time
        try {
            // if given empty arguments or space as task
            String dummyTask = input.split(" ")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\u2639 Oops, the description of "
                + tasktype.toString().toLowerCase() + " cannot be empty");
        }

        if (tasktype == Task.taskType.TODO) {
            // Without time
            String taskDesc = input.substring(5);
            return new Todo(taskDesc);
        }
        if (tasktype == Task.taskType.DEADLINE) {
            try {
                return parseTaskWithTime(input, tasktype, "/by");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 Oops wrong format, use add deadline format: " +
                    "deadline [task] /by [time (can be 'YYYY-MM-DD HHMM')] !");
            }
        } else if (tasktype == Task.taskType.EVENT) {
            try {
                return parseTaskWithTime(input, tasktype, "/at");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 Oops wrong format, use add event format: " +
                    "event [task] /at [time] !");
            }
        } else {
            return new Task("this task should not be created");
        }
    }

    /**
     * Processes user input into an event or a deadline, which are both tasks with time.
     *
     * @param input User input.
     * @param tasktype Type of task.
     * @param separator String separating description and time.
     * @return New Task to be added.
     * @throws DukeException if description or time is empty.
     */
    public static Task parseTaskWithTime(String input, Task.taskType tasktype, String separator) throws DukeException {
        String taskDesc = input.substring(tasktype.name().length() + 1, input.indexOf(separator) - 1);
        checkIsFieldEmpty("taskDesc", taskDesc);
        // +4 due to size of /by or /at with a space
        String time = input.substring(input.indexOf(separator) + 4);
        checkIsFieldEmpty("time", time);
        if (tasktype ==  Task.taskType.DEADLINE) {
            return new Deadline(taskDesc, time);
        } else {
            return new Event(taskDesc, time);
        }
    }

    /**
     * Checks whether the given field is empty.
     *
     * @param nameOfField Task description or time.
     * @param field String given.
     * @throws DukeException if field is empty.
     */
    public static void checkIsFieldEmpty(String nameOfField, String field) throws DukeException {
        // check whether the argument given is empty
        if (field.trim().isEmpty()) {
            throw new DukeException("\u2639 Oops, " + nameOfField + " cannot be empty!");
        }
    }
}
