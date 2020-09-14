package duke.main;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles the logic flow of Duke.
 */
public class Processor {

    /**
     * Handles the case when user input bye command.
     *
     * @param ui The UI that handles interaction between Duke and user.
     */
    public static void handleByeCommand(StringBuilder response, Ui ui) {
        String goodbyeMessage = ui.getGoodByeMessage();
        response.append(goodbyeMessage);
        ui.printToScreen(goodbyeMessage);
    }

    /**
     * Handles the case when user input list command.
     *
     * @param response Duke's response to user's input.
     * @param ui       The UI that handles interaction between Duke and user.
     * @param tasks    User's task list.
     */
    public static void handleListCommand(StringBuilder response, Ui ui, TaskList tasks) {
        String headerMessage = "Here are the tasks in your list:\n";
        String taskListInString = ui.getFullList(headerMessage, tasks);
        response.append(taskListInString);
    }

    /**
     * Handles the case when user input done command.
     *
     * @param userInput The user's input.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
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
     *
     * @param userInput The user's input.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
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
     *
     * @param userInput The user's input.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
     * @throws DukeException can be thrown when task description is empty.
     */
    public static void handleTodoCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        String argument = Parser.getArgs(userInput);
        String taskDescription = Parser.findDescription(argument);
        boolean hasPriority = Parser.hasPriority(userInput);

        Task task;
        if (hasPriority) {
            String priority = Parser.getPriority(userInput);
            task = new ToDo(taskDescription, false, priority);
        } else {
            task = new ToDo(taskDescription, false, "low");
        }

        tasks.addTask(task);
        String message = ui.getAddTaskMessage(task, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input deadline command.
     *
     * @param userInput The user's input.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
     * @throws DukeException can be thrown when the task description or the time is empty, or
     *                       date format is incorrect.
     */
    public static void handleDeadlineCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws DukeException {
        String argument = Parser.getArgs(userInput);
        String taskDescription = Parser.findDescription(argument);
        String deadLineTime = Parser.findTime(argument, "by");
        // check whether the date time format is correct
        Parser.isValidDate(deadLineTime);
        // check whether time is included
        boolean hasTime = Parser.hasTime(deadLineTime);
        boolean hasPriority = Parser.hasPriority(userInput);

        Task deadLine;
        if (hasPriority) {
            String priority = Parser.getPriority(userInput);
            deadLine = new DeadLine(taskDescription, deadLineTime, hasTime, false, priority);
        } else {
            deadLine = new DeadLine(taskDescription, deadLineTime, hasTime, false, "low");
        }

        tasks.addTask(deadLine);
        String message = ui.getAddTaskMessage(deadLine, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input event command.
     *
     * @param userInput The user's input.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
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
        boolean hasPriority = Parser.hasPriority(userInput);

        Task event;
        if (hasPriority) {
            String priority = Parser.getPriority(userInput);
            event = new Event(taskDescription, eventTime, hasTime, false, priority);
        } else {
            event = new Event(taskDescription, eventTime, hasTime, false, "low");
        }

        tasks.addTask(event);
        String message = ui.getAddTaskMessage(event, tasks);
        response.append(message);
    }

    /**
     * Handles the case when user input find command.
     *
     * @param userInput The user's input.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
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
     * Handles the case when user input sort command
     *
     * @param userInput Input from the user.
     * @param response  Duke's response to user's input.
     * @param ui        The UI that handles interaction between Duke and user.
     * @param tasks     User's task list.
     * @throws InvalidCommandException When the command given is invalid.
     */
    public static void handleSortCommand(
            String userInput, StringBuilder response, Ui ui, TaskList tasks) throws InvalidCommandException {
        if (Parser.isSortedByPriority(userInput)) {
            TaskList sortedList = tasks.sortPriority();
            String headerMessage = "Here is your task list sorted based on priority:\n";
            String message = ui.getFullList(headerMessage, sortedList);
            response.append(message);
        } else {
            TaskList sortedList = tasks.sortDescription();
            String headerMessage = "Here is your task list sorted alphabetically:\n";
            String message = ui.getFullList(headerMessage, sortedList);
            response.append(message);
        }
    }

    private static boolean isValidCommand(String command) throws InvalidCommandException {
        if (Keyword.isValid(command)) {
            return true;
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Processes the run of the program.
     *
     * @param tasks   The user's task list.
     * @param storage The storage storing the user's saved task list.
     * @param ui      UI that handles interaction with user.
     * @throws DukeException Exception can be thrown due to multiple reasons, such as
     *                       invalid command, empty task description, empty date or
     *                       wrong date format.
     */
    public static void process(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        while (Parser.hasNextLine()) {
            String userInput = Parser.readNextLine();
            String command = Parser.getCommand(userInput);
            StringBuilder response = new StringBuilder();
            isValidCommand(command);
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
            } else if (command.equals("sort")) {
                handleSortCommand(userInput, response, ui, tasks);
            } else {
                throw new InvalidCommandException();
            }
            storage.writeTasks(tasks);
            ui.printToScreen(response.toString());
        }
    }
}
