import java.util.List;

/**
 * Encapsulates the parsing system for user commands.
 */
public class Parser {

    /**
     * Duke components required when handling user commands.
     */
    private TaskList taskList;
    private DukeSaver saver;

    /**
     * Constructor for parsing system.
     *
     * @param taskList List of tasks.
     * @param saver Saving system.
     */
    public Parser(TaskList taskList, DukeSaver saver) {
        this.taskList = taskList;
        this.saver = saver;
    }

    /**
     * Handles a user command and designates task to helper functions.
     *
     * @param response User's command.
     * @throws DukeException Thrown when invalid commands are invoked.
     *
     * @return A string response.
     */
    public String handleResponse(String response) throws DukeException {
        String[] parsedResponse = response.split(" ", 2);
        Command command;
        try {
            command = Command.valueOf(parsedResponse[0].toUpperCase());
        } catch (IllegalArgumentException ex) {
            command = Command.INVALID;
        }
        String rest = parsedResponse.length == 1 ? null : parsedResponse[1];
        switch (command) {
        case BYE:
            saver.saveData(taskList);
            System.exit(0);
            return null;
        case LIST:
            return handleList();
        case DONE:
            return handleDone(rest);
        case TODO:
            return handleTodo(rest);
        case DEADLINE:
            return handleDeadline(rest);
        case EVENT:
            return handleEvent(rest);
        case DELETE:
            return handleDelete(rest);
        case FIND:
            return handleFind(rest);
        case INVALID:
            throw new DukeException("Unrecognized command!");
        default:
            throw new DukeException("Passed through invalid command switch case. Should not have happened.");
        }
    }

    /**
     * Handles the "list" command by iterating through all
     * tasks in the list and building a string.
     * Prints the final string.
     */
    private String handleList() {
        if (taskList.isEmpty()) {
            return "You have no tasks!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); ++i) {
            sb.append((i + 1) + ". " + taskList.get(i) + "\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Handles the "done" command by looking for the task
     * at the index and marking it as done.
     *
     * @param rest The remaining string after the key command "done".
     * @throws DukeException Thrown when invalid format used.
     */
    private String handleDone(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Specify a task!");
        }
        int taskDone;
        try {
            taskDone = Integer.parseInt(rest) - 1;
        } catch (NumberFormatException ex) {
            throw new DukeException("Specify a valid task number!");
        }
        if (taskDone < 0 || taskDone >= taskList.size()) {
            throw new DukeException("No such task!");
        }
        taskList.markTaskDone(taskDone);
        return "Nice! I've marked this task as done:\n" + taskList.get(taskDone);
    }

    /**
     * Handles the creation of a Todo task.
     *
     * @param rest The remaining string after the key command "todo".
     * @throws DukeException Thrown when invalid format used.
     */
    private String handleTodo(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Description of a todo cannot be empty!");
        }
        Todo todo = new Todo(rest);
        taskList.addTask(todo);
        return "Got it. I've added this task:\n" + todo + "\n" + taskList.taskSizeString();
    }

    /**
     * Handles the creation of a Deadline task.
     *
     * @param rest The remaining string after the key command "deadline".
     * @throws DukeException Thrown when invalid format used.
     */
    private String handleDeadline(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Description of a deadline cannot be empty!");
        }
        String[] deadlineParsed = rest.split("/");
        if (deadlineParsed.length == 1) {
            throw new DukeException("Prefix the keyword 'by' with a forward slash!");
        }
        String deadlineName = deadlineParsed[0].trim();
        String[] byParsed = deadlineParsed[1].split(" ", 2);
        if (byParsed.length == 1) {
            throw new DukeException("Deadline due time cannot be empty!");
        }
        String by = byParsed[1];
        Deadline deadline = new Deadline(deadlineName, by);
        taskList.addTask(deadline);
        return "Got it. I've added this task:\n" + deadline + "\n" + taskList.taskSizeString();
    }

    /**
     * Handles the creation of an Event task.
     *
     * @param rest The remaining string after the key command "event".
     * @throws DukeException Thrown when invalid format used.
     */
    private String handleEvent(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Description of an event cannot be empty!");
        }
        String[] eventParsed = rest.split("/");
        if (eventParsed.length == 1) {
            throw new DukeException("Prefix the keyword 'at' with a forward slash!");
        }
        String eventName = eventParsed[0].trim();
        String[] atParsed = eventParsed[1].split(" ", 2);
        if (atParsed.length == 1) {
            throw new DukeException("Event time cannot be empty!");
        }
        String at = atParsed[1];
        Event event = new Event(eventName, at);
        taskList.addTask(event);
        return "Got it. I've added this task:\n" + event + "\n" + taskList.taskSizeString();
    }

    /**
     * Handles the deletion of a task.
     *
     * @param rest The remaining string after the key command "delete", which is index of task.
     * @throws DukeException Thrown when invalid format used.
     */
    private String handleDelete(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Specify a task!");
        }
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(rest) - 1;
        } catch (NumberFormatException ex) {
            throw new DukeException("Specify a valid task number!");
        }
        if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
            throw new DukeException("No such task!");
        }
        Task taskToDelete = taskList.get(deleteIndex);
        taskList.deleteTask(deleteIndex);
        return "Noted. I've removed this task:\n" + taskToDelete + "\n" + taskList.taskSizeString();
    }

    /**
     * Handles the "find" command.
     *
     * @param rest Remaining string after the "find" command, which is query term.
     * @throws DukeException Thrown when no search term is given.
     */
    private String handleFind(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Specify a search term!");
        }

        List<Task> matches = taskList.search(rest);

        if (matches.isEmpty()) {
            return "No matching task.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < matches.size(); ++i) {
            sb.append((i + 1) + "." + matches.get(i) + "\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
