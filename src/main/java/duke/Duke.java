package duke;

import java.util.ArrayList;

/**
 * This class handles the logic behind the Duke chatbot
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private InputParser inputParser;
    private TaskList userTasks;

    /**
     * Instantiates a new Duke chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        inputParser = new InputParser();
        userTasks = new TaskList(storage.readFromFile());
    }

    /**
     * Gets user input and response +=s an appropriate string response.
     *
     * @param input This is the string input from user of Duke chatbot.
     * @return String response to user input.
     * @throws DukeException Any exception caught will throw a DukeException.
     */
    public String getResponse (String input) throws DukeException {

        Task t = new Task("");
        String[] inputSplit = new String[] {};
        String description, response = "";

        // Get input from user
//        input = inputParser.getInput();

        CommandType command = inputParser.parseInput(input);

        switch (command) {
        case HELP:
            response += ui.printAvailableCommands();
            break;
        case TODO:
            description = input.substring(4);
            t = new ToDo(description);
            userTasks.addTask(t);
            storage.saveToFile(userTasks.getTaskList());
            response += ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
            break;
        case DEADLINE:
            inputSplit = input.split(" /by ");
            String by = inputSplit[1];
            description = inputSplit[0].substring(8);
            t = new Deadline(description, by);
            userTasks.addTask(t);
            storage.saveToFile(userTasks.getTaskList());
            response += ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
            break;
        case EVENT:
            inputSplit = input.split(" /at ");
            String at = inputSplit[1].split(" ")[0];
            String timeRange = inputSplit[1].split(" ")[1];
            description = inputSplit[0].substring(5);
            t = new Event(description, at, timeRange);
            userTasks.addTask(t);
            storage.saveToFile(userTasks.getTaskList());
            response += ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
            break;
        case LIST_ALL:
            response += ui.printAllTasks(userTasks.getTaskList());
            break;
        case LIST_ALL_DONE:
            ArrayList<Task> tasksDone = new TaskFinder()
                    .findAllDone(userTasks.getTaskList());
            response += ui.printAllTasksDone(tasksDone);
            break;
        case LIST_ALL_NOT_DONE:
            ArrayList<Task> tasksNotDone = new TaskFinder()
                    .findAllNotDone(userTasks.getTaskList());
            response += ui.printAllTasksNotDone(tasksNotDone);
            break;
        case LIST_TODOS:
            ArrayList<Task> toDos = new TaskFinder()
                    .findToDos(userTasks.getTaskList());
            response += ui.printToDos(toDos);
            break;
        case LIST_TODOS_DONE:
            ArrayList<Task> toDosDone = new TaskFinder()
                    .findToDosDone(userTasks.getTaskList());
            response += ui.printToDosDone(toDosDone);
            break;
        case LIST_TODOS_NOT_DONE:
            ArrayList<Task> toDosNotDone = new TaskFinder()
                    .findToDosNotDone(userTasks.getTaskList());
            response += ui.printToDosNotDone(toDosNotDone);
            break;
        case LIST_DEADLINES:
            ArrayList<Task> deadlines = new TaskFinder()
                    .findDeadlines(userTasks.getTaskList());
            response += ui.printDeadlines(deadlines);
            break;
        case LIST_DEADLINES_DONE:
            ArrayList<Task> deadlinesDone = new TaskFinder()
                    .findDeadlinesDone(userTasks.getTaskList());
            response += ui.printDeadlinesDone(deadlinesDone);
            break;
        case LIST_DEADLINES_NOT_DONE:
            ArrayList<Task> deadlinesNotDone = new TaskFinder()
                    .findDeadlinesNotDone(userTasks.getTaskList());
            response += ui.printDeadlinesNotDone(deadlinesNotDone);
            break;
        case LIST_EVENTS:
            ArrayList<Task> events = new TaskFinder()
                    .findEvents(userTasks.getTaskList());
            response += ui.printEvents(events);
            break;
        case LIST_EVENTS_DONE:
            ArrayList<Task> eventsDone = new TaskFinder()
                    .findEventsDone(userTasks.getTaskList());
            response += ui.printEventsDone(eventsDone);
            break;
        case LIST_EVENTS_NOT_DONE:
            ArrayList<Task> eventsNotDone = new TaskFinder()
                    .findEventsNotDone(userTasks.getTaskList());
            response += ui.printEventsNotDone(eventsNotDone);
            break;
        case LIST_BY_KEYWORD:
            String keyword = input.split("find ")[1];
            ArrayList<Task> filteredTasks = new TaskFinder()
                    .findByKeyword(userTasks.getTaskList(), keyword);
            response += ui.printFilteredTasksByKeyword(filteredTasks, keyword);
            break;
        case DONE:
            // Get the index stated after "done" by parsing the string
            int index = Integer.parseInt(input.substring(5)) - 1;

            // Mark item as done
            try {
                if (index >= userTasks.getTaskListSize()) {
                    throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                } else {
                    userTasks.markTaskAsDone(index);
                    response += ui.printMarkAsDoneMessage(userTasks.getTask(index));
                }

                // Update Tasklist.txt after marking task as done
                storage.saveToFile(userTasks.getTaskList());

            } catch (DukeException ex) {
                response += ui.printError(ex);
            }
            break;
        case DELETE:
            // Get the index stated after "delete" by parsing the string
            index = Integer.parseInt(input.substring(7)) - 1;

            // Delete item
            try {
                if (index >= userTasks.getTaskListSize()) {
                    throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                } else {
                    t = userTasks.getTask(index);
                    userTasks.deleteTask(index);
                    response += ui.printTaskDeletedMessage(t);
                }

                // Update Tasklist.txt after removing task
                storage.saveToFile(userTasks.getTaskList());

            } catch (DukeException ex) {
                response += ui.printError(ex);
            }
            break;
        case BYE:
            response += ui.printByeMessage();
        break;
        case INVALID_IS_EMPTY:
            try {
                throw new DukeException("", ExceptionType.EMPTY_INPUT);
            } catch (DukeException ex) {
                response += ui.printError(ex);
                break;
            }
        case INVALID_COMMAND:
            try {
                throw new DukeException("", ExceptionType.INVALID_COMMAND);
            } catch (DukeException ex) {
                response += ui.printError(ex);
                break;
            }
        case INVALID_EMPTY_DESCRIPTION:
            try {
                throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
            } catch (DukeException ex) {
                response += ui.printError(ex);
                break;
            }
        case INVALID_DEADLINE_NO_BY:
            try {
                throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
            } catch (DukeException ex) {
                response += ui.printError(ex);
                break;
            }
        case INVALID_EVENT_NO_START_END:
            try {
                throw new DukeException("", ExceptionType.EVENT_NO_START_END);
            } catch (DukeException ex) {
                response += ui.printError(ex);
                break;
            }
        }
        return response;
    }

    /**
     * Returns the Ui object of Duke to caller.
     *
     * @return Ui object.
     */
    public Ui getUi() {
        return ui;
    }
}