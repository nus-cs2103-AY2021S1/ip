package duke.main;

import duke.exception.DukeException;

import duke.exception.InvalidCommandException;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;

/**
 * Handles the logic flow of Duke.
 */
public class Processor {

    private static boolean isValidIndex(int index, TaskList tasks) {
        return index > 0 && index <= tasks.getSize();
    }

    /**
     * Handles the case when user input bye command.
     * @param ui The UI that handles interaction between program and user.
     */
    public static void handleByeCommand(StringBuilder response, Ui ui) {
        String goodbyeMessage = ui.getGoodByeMessage();
        response.append(goodbyeMessage);
        ui.printToScreen(goodbyeMessage);
    }

    /**
     * Handles the case when user input list command.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     */
    public static void handleListCommand(StringBuilder response, Ui ui, TaskList tasks) {
        String taskListInString = ui.getFullList(tasks);
        response.append(taskListInString);
    }

    /**
     * Handles the case when user input done command.
     * @param userInput The user's input.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     * @throws DukeException when the index is invalid.
     */
    public static void handleDoneCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        int taskNumber = Parser.getIndexTask(userInput);
        int taskIndex = taskNumber - 1;
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        String message = ui.getMarkTaskAsDoneMessage(task);
        response.append(message);
    }

    /**
     * Handles the case when user input delete command.
     * @param userInput The user's input.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     * @throws DukeException when the index is invalid.
     */
    public static void handleDeleteCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        int taskNumber = Parser.getIndexTask(userInput);
        int taskIndex = taskNumber - 1;
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        String message = ui.getDeleteTaskMessage(deletedTask, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input todo command.
     * @param userInput The user's input.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     * @throws DukeException can be thrown when task description is empty.
     */
    public static void handleTodoCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        String argument = Parser.getArgs(userInput);
        String taskDescription = Parser.findDescription(argument);
        Task task = new ToDo(taskDescription);
        tasks.addTask(task);
        String message = ui.getAddTaskMessage(task, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input deadline command.
     * @param userInput The user's input.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     * @throws DukeException can be thrown when the task description or the time is empty, or
     *                       date format is incorrect.
     */
    public static void handleDeadlineCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        String argument = Parser.getArgs(userInput);
        String taskDescription = Parser.findDescription(argument);
        String deadlineTime = Parser.findTime(argument, "by");
        // check whether the date time format is correct
        Parser.isValidDate(deadlineTime);
        // check whether time is included
        boolean hasTime = Parser.hasTime(deadlineTime);
        Task deadLine = new DeadLine(taskDescription, deadlineTime, hasTime, false);
        tasks.addTask(deadLine);
        String message = ui.getAddTaskMessage(deadLine, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input event command.
     * @param userInput The user's input.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     * @throws DukeException can be thrown when the task description or the time is empty, or
     *                       date format is incorrect.
     */
    public static void handleEventCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        String argument = Parser.getArgs(userInput);
        String taskDescription = Parser.findDescription(argument);
        String eventTime = Parser.findTime(argument, "at");
        // check whether the date time format is correct
        Parser.isValidDate(eventTime);
        // check whether time is included
        boolean hasTime = Parser.hasTime(eventTime);
        Task event = new Event(taskDescription, eventTime, hasTime, false);
        tasks.addTask(event);
        String message = ui.getAddTaskMessage(event, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input find command
     * @param userInput The user's input.
     * @param response The program's response to user's input.
     * @param ui The UI that handles interaction between program and user.
     * @param tasks User's task list.
     * @throws DukeException can be thrown when the task description or the time is empty, or
     *                       date format is incorrect.
     */
    public static void handleFindCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        String keyword = Parser.getArgs(userInput);
        TaskList correspondTaskList = tasks.findTaskWithKeyword(keyword);
        String message = ui.getMatchingList(correspondTaskList);
        response.append(message);
    }

    /**
     * Processes the run of the program.
     * @param tasks The user's task list.
     * @param storage The storage storing the user's saved task list.
     * @param ui UI that handles interaction with user.
     * @throws DukeException Exception can be thrown due to multiple reasons, such as
     *                       invalid command, empty task description, empty date or
     *                       wrong date format.
     */
    public static void process(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        while (Parser.hasNextLine()) {
            String userInput = Parser.readNextLine();
            String command = Parser.getCommand(userInput);
            StringBuilder response = new StringBuilder();
            if (command.equals("bye")) {
                handleByeCommand(response, ui);
                break;
            } else if (command.equals("list")) {
                handleListCommand(response, ui, tasks);
            } else if (command.equals("done")) {
                handleDoneCommand(userInput, response, ui, tasks);
            } else if (command.equals("delete")) {
                handleDeleteCommand(userInput, response, ui, tasks);
            } else if (command.equals("todo")) {
                handleTodoCommand(userInput, response, ui, tasks);
            } else if (command.equals("deadline")) {
                handleDeadlineCommand(userInput, response, ui, tasks);
            } else if (command.equals("event")) {
                handleEventCommand(userInput, response, ui, tasks);
            } else if (command.equals("find")) {
                handleFindCommand(userInput, response, ui, tasks);
            } else {
                throw new InvalidCommandException();
            }
            storage.writeTasks(tasks);
            ui.printToScreen(response.toString());
        }
    }
}
