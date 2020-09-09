package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.TimeParser;
import duke.task.Todo;

public class Parser {
    private static TaskList taskList;

    /**
     * Sets taskList for Parser.
     *
     * @param list TaskList that manages tasks.
     */
    public static void setTaskList(TaskList list) {
        taskList = list;
    }

    /**
     * Returns a boolean to indicate whether program should be stopped.
     *
     * @param command Command from user input.
     * @return Returns a boolean.
     */
    public static boolean stopProgram(String command) {
        return command.equals("bye");
    }

    /**
     * Processes commands and add the tasks to taskList.
     *
     * @param command Command from user input.
     * @throws DukeException DukeException if command is not in legal form.
     */
    public static String parseCommand(String command) throws DukeException {
        if (command.equals("hi") || command.equals("bye") || command.equals("list")) {
            return parseOneWordCommand(command);
        } else {
            String[] strArr = command.split(" ", 2);
            String taskType = strArr[0];
            String userInput = "";
            if (strArr.length != 1) {
                userInput = strArr[1];
            }
            switch (taskType) {
            case "done":
                return parseDoneCommand(userInput);
            case "tag":
                return parseTagCommand(userInput);
            case "delete":
                return parseDeleteCommand(userInput);
            case "find":
                return parseFindCommand(userInput);
            case "todo":
                return parseToDoCommand(userInput);
            case "deadline":
                return parseDeadlineCommand(userInput);
            case "event":
                return parseEventCommand(userInput);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Processes hi, bye, list command.
     *
     * @param command A hi, bye, or list command.
     * @return The response string.
     * @throws DukeException DukeException.
     */
    public static String parseOneWordCommand(String command) throws DukeException {
        switch (command) {
        case "hi":
            return Ui.greet();
        case "bye":
            Storage.writeToFile(taskList);
            return Ui.getExitMessage();
        case "list":
            return taskList.taskListToString();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Processes done command.
     *
     * @param input Details of done command.
     * @return The response string.
     */
    public static String parseDoneCommand(String input) {
        int taskNumber = Integer.parseInt(input);
        return taskList.markTaskAsDone(taskNumber);
    }

    /**
     * Processes tag command.
     *
     * @param userInput Details of tag command.
     * @return The response string.
     */
    public static String parseTagCommand(String userInput) {
        String[] data = userInput.split(" ", 2);
        int taskNumber = Integer.parseInt(data[0]);
        String tagName = data[1];
        return taskList.tagTaskInList(taskNumber, tagName);

    }

    /**
     * Processes delete command.
     *
     * @param userInput Details of delete command.
     * @return The response string.
     */
    public static String parseDeleteCommand(String userInput) throws DukeException {
        int taskNumber = Integer.parseInt(userInput);
        if (taskNumber <= taskList.getNumOfTask()) {
            return taskList.deleteTask(taskNumber);
        } else {
            throw new DukeException("You only have " + taskList.getNumOfTask() + " tasks in your task list.");
        }
    }

    /**
     * Processes find command.
     *
     * @param userInput Details of find command.
     * @return The response string.
     */
    public static String parseFindCommand(String userInput) {
        return taskList.findTaskByKeyword(userInput);
    }

    /**
     * Processes to do command.
     *
     * @param userInput Details of to do command.
     * @return The response string.
     * @throws DukeException DukeException if description of a to do is empty.
     */
    public static String parseToDoCommand(String userInput) throws DukeException {
        if (userInput.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(userInput);
            taskList.addTask(todo);
            return taskList.addedTaskToString(todo);
        }
    }

    /**
     * Processes deadline command.
     *
     * @param userInput Details of deadline command.
     * @return The response string.
     * @throws DukeException DukeException if description of a deadline is in wrong format.
     */
    public static String parseDeadlineCommand(String userInput) throws DukeException {
        if (userInput.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else {
            String[] deadlineArr = userInput.split(" /by ", 2);
            if (deadlineArr.length != 2) {
                throw new DukeException("Wrong format when describing a deadline.");
            } else if (TimeParser.isValidTime(deadlineArr[1])) {
                System.out.println(deadlineArr[1]);
                String deadlineTime = TimeParser.parseTime(deadlineArr[1]);
                Deadline deadline = new Deadline(deadlineArr[0], deadlineTime);
                taskList.addTask(deadline);
                return taskList.addedTaskToString(deadline);
            } else {
                throw new DukeException("Wrong format when describing a date.");
            }
        }
    }

    /**
     * Processes event command.
     *
     * @param userInput Details of event command.
     * @return The response string.
     * @throws DukeException DukeException if description of an event is empty.
     */
    public static String parseEventCommand(String userInput) throws DukeException {
        if (userInput.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        } else {
            String[] eventArr = userInput.split(" /at ", 2);
            if (eventArr.length != 2) {
                throw new DukeException("Wrong format when describing an event.");
            } else if (TimeParser.isValidTime(eventArr[1])) {
                String eventTime = TimeParser.parseTime(eventArr[1]);
                Event event = new Event(eventArr[0], eventTime);
                taskList.addTask(event);
                return taskList.addedTaskToString(event);
            } else {
                throw new DukeException("Wrong format when describing a date.");
            }
        }
    }

    /**
     * Returns the taskList.
     *
     * @return TaskList.
     */
    public static TaskList getTaskList() {
        return taskList;
    }
}
