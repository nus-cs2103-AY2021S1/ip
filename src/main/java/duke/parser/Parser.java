package duke.parser;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.dukeexception.DukeKeywordMissingException;
import duke.dukeexception.DukeMissingCommandNumberException;
import duke.dukeexception.DukeTaskNonExistException;
import duke.dukeexception.DukeUnknownInputException;
import duke.dukeexception.EmptyDescriptionException;
import duke.ui.UI;

/**
 * This class deals with the interpretation of the user's inputs by the program.
 */
public class Parser {
    private final UI ui;

    /**
     * Constructor for the parser.
     *
     * @param ui UI which deals with interactions with the user.
     */
    public Parser(UI ui) {
        this.ui = ui;
    }

    public String welcome() {
        return ui.performWelcome();
    }

    /**
     * Method that takes in different commands by the user and calls the relevant methods in the UI to deal with them.
     *
     * <p>
     * <h3> Userinput = bye</h3>
     * Stops the entire program
     * </p>
     *
     * <p>
     * <h3> UserInput = list</h3>
     * Prints out a list of the tasks in the TaskList
     * </p>
     *
     * <p>
     * <h3> UserInput = find xxx task</h3>
     * Finds the tasks that match the keyword provided by the user and prints it out as a list
     * </p>
     *
     * <p>
     * <h3> UserInput = delete xxx task</h3>
     * Triggers the UI to delete the task and converts the one-based index given by the user
     * to a zero-based index.
     * </p>
     *
     * <p>
     * <h3> UserInput = done</h3>
     * Triggers the UI to mark the task as done and converts the one-based index given by the user
     * to a zero-based index.
     * </p>
     *
     * <p>
     * <h3> UserInput = create Event/Deadline/Todo </h3>
     * Triggers the UI to create the corresponding task object and separates the user's input to
     * show the name of the task, type of task and the duedate of the task(only applicable for deadline and events).
     * It may throw an error if the description is empty, keywords are missing or the dates are in the wrong format.
     * </p>
     *
     * @param response String response which represents the user's input.
     */
    public String listen(String response) {
        try {
            int taskIndex;
            switch(keyword(response)) {
            case BYE:
                return ui.performBye();
            case LIST:
                return ui.performShowList();
            case FIND:
                checkEmptyDescription(response, "Find Query");
                return ui.performFind(response.substring(5));
            case DELETE:
                taskIndex = getTaskIndex(response);
                return ui.performDelete(taskIndex);
            case DONE:
                taskIndex = getTaskIndex(response);
                return ui.performDone(taskIndex);
            case TODO:
                return processTodo(response);
            case DEADLINE:
                return processDeadline(response);
            case EVENT:
                return processEvent(response);
            case UPDATE:
                return processUpdate(response);
            case UNKNOWN:
                throw new DukeUnknownInputException("error");
            default:
                assert false : "Parser unidentified response";
                return null;
            }
        } catch (IOException | DukeTaskNonExistException | EmptyDescriptionException
                | DukeKeywordMissingException | DukeUnknownInputException
                | DukeMissingCommandNumberException e) {
            return ui.performShowError(e);
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Ensure that the datetime input is in the format YYYY-MM-DD HH:MM";
        }
    }

    private String processUpdate(String response) throws EmptyDescriptionException, DukeKeywordMissingException, DukeMissingCommandNumberException, IOException {
        int taskIndex;
        checkEmptyDescription(response, "update");
        checkMissingKeyword(response, "/to ");
        taskIndex = getTaskIndex(response);
        response = response.substring(response.indexOf("/to ") + "/to ".length());
        Command taskType = getTaskType(response);
        return processUpdatedTask(taskIndex, response, taskType);
    }

    private String processUpdatedTask(int taskIndex, String response, Command taskType) throws
            DukeKeywordMissingException, EmptyDescriptionException, IOException {
        String date;
        switch(taskType) {
        case EVENT:
            checkEmptyDescription(response, "event");
            checkMissingKeyword(response, "/at ");
            date = getDate(response, "/at ");
            response = getResponse(response, "event ", "/at ");
            return ui.performUpdateTask(response, taskIndex, Command.EVENT, date);
        case DEADLINE:
            checkEmptyDescription(response, "deadline");
            checkMissingKeyword(response, "/by ");
            date = getDate(response, "/by ");
            response = getResponse(response, "deadline ", "/by ");
            return ui.performUpdateTask(response, taskIndex, Command.DEADLINE, date);
        case TODO:
            checkEmptyDescription(response, "todo");
            response = response.substring("todo".length());
            return ui.performUpdateTask(response, taskIndex, Command.TODO, "");
        default:
            assert false : "Duke incorrect tasktype in processUpdatedTask";
            throw new DukeKeywordMissingException("no such task type");
        }
    }

    private String processTodo(String response) throws EmptyDescriptionException, IOException {
        checkEmptyDescription(response, "todo");
        return ui.performAddTodo(response.substring("todo".length()));
    }

    private String processEvent(String response) throws EmptyDescriptionException, DukeKeywordMissingException,
            IOException {
        String date;
        checkEmptyDescription(response, "event");
        checkMissingKeyword(response, "/at ");
        date = getDate(response, "/at ");
        response = getResponse(response, "event ", "/at ");
        return ui.performAddEvent(response, date);
    }

    private String processDeadline(String response) throws EmptyDescriptionException,
            DukeKeywordMissingException, IOException {
        String date;
        checkEmptyDescription(response, "deadline");
        checkMissingKeyword(response, "/by ");
        date = getDate(response, "/by ");
        response = getResponse(response, "deadline ", "/by ");
        return ui.performAddDeadline(response, date);
    }

    private Command getTaskType(String response) throws DukeKeywordMissingException {
        int endPoint = response.indexOf(" ");
        if (endPoint < 0) {
            throw new DukeKeywordMissingException("Missing Keyword : type of task");
        }
        switch (response.substring(0, response.indexOf(" "))) {
        case "todo":
            return Command.TODO;
        case "event":
            return Command.EVENT;
        case "deadline":
            return Command.DEADLINE;
        default:
            throw new DukeKeywordMissingException("Missing Keyword : type of task");
        }
    }

    private String getResponse(String response, String s, String connector) {
        return response.substring(response.indexOf(s) + s.length() - " ".length(),
                response.indexOf(connector));
    }

    private String getDate(String response, String s) {
        return response.substring(response.indexOf(s) + s.length());
    }

    private void checkMissingKeyword(String response, String s) throws DukeKeywordMissingException {
        if (!response.contains(s)) {
            throw new DukeKeywordMissingException(s);
        }
    }

    private void checkEmptyDescription(String response, String s) throws EmptyDescriptionException {
        if (response.length() <= s.length() + " ".length()) {
            throw new EmptyDescriptionException(s);
        }
    }

    private int getTaskIndex(String response) throws DukeMissingCommandNumberException {
        int taskIndex;
        String parsedResponse = getCommand(response);
        taskIndex = parseInteger(parsedResponse);
        return taskIndex;
    }

    private int parseInteger(String parsedResponse) throws DukeMissingCommandNumberException {
        try {
            return Integer.parseInt(parsedResponse.replaceAll("\\D+", "")) - 1;
        } catch (NumberFormatException e) {
            throw new DukeMissingCommandNumberException("error");
        }
    }

    private String getCommand(String response) {
        return response.substring(0, response.indexOf(" ", response.indexOf(" ") + 1));
    }

    private Command keyword(String response) {
        if (response.equals("bye")) {
            return Command.BYE;
        } else if (response.equals("list")) {
            return Command.LIST;
        } else if (response.indexOf("find ") == 0) {
            return Command.FIND;
        } else if (response.indexOf("update ") == 0) {
            return Command.UPDATE;
        } else if (response.indexOf("delete") == 0) {
            return Command.DELETE;
        } else if (response.indexOf("done ") == 0) {
            return Command.DONE;
        } else if (response.indexOf("todo ") == 0) {
            return Command.TODO;
        } else if (response.indexOf("deadline ") == 0) {
            return Command.DEADLINE;
        } else if (response.indexOf("event ") == 0) {
            return Command.EVENT;
        } else {
            return Command.UNKNOWN;
        }
    }
}
