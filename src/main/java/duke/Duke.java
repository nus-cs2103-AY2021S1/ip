package duke;

import duke.command.ByeCommand;
import duke.command.HelpCommand;

import java.util.ArrayList;

/**
 * This class handles the logic behind the Duke chatbot.
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
     * Gets user input and returns an appropriate string response.
     *
     * @param input This is the string input from user of Duke chatbot.
     * @return String response to user input.
     * @throws DukeException Any exception caught will throw a DukeException.
     */
    public String getResponse (String input) throws DukeException {

        Task task = new Task("");
        String[] inputSplit = new String[] {};
        String description, response = "";

        CommandType command = inputParser.parseInput(input);

        switch (command) {
        case HELP:
            response += new HelpCommand().getResponse();
            break;
        case TODO:
            description = input.substring(4);
            task = new ToDo(description);
            userTasks.addTask(task);
            storage.saveToFile(userTasks.getTaskList());
            response += ui.taskAddedMessage(task, userTasks.getTaskListSize());
            break;
        case DEADLINE:
            inputSplit = input.split(" /by ");
            String by = inputSplit[1];
            description = inputSplit[0].substring(8);
            task = new Deadline(description, by);
            userTasks.addTask(task);
            storage.saveToFile(userTasks.getTaskList());
            response += ui.taskAddedMessage(task, userTasks.getTaskListSize());
            break;
        case EVENT:
            inputSplit = input.split(" /at ");
            String at = inputSplit[1].split(" ")[0];
            String timeRange = inputSplit[1].split(" ")[1];
            description = inputSplit[0].substring(5);
            task = new Event(description, at, timeRange);
            userTasks.addTask(task);
            storage.saveToFile(userTasks.getTaskList());
            response += ui.taskAddedMessage(task, userTasks.getTaskListSize());
            break;
        case LIST_ALL:
            response += ui.allTasksToString(userTasks.getTaskList());
            break;
        case LIST_ALL_DONE:
            ArrayList<Task> tasksDone = new TaskFinder()
                    .findAllDone(userTasks.getTaskList());
            response += ui.allTasksDoneToString(tasksDone);
            break;
        case LIST_ALL_NOT_DONE:
            ArrayList<Task> tasksNotDone = new TaskFinder()
                    .findAllNotDone(userTasks.getTaskList());
            response += ui.allTasksNotDoneToString(tasksNotDone);
            break;
        case LIST_TODOS:
            ArrayList<Task> toDos = new TaskFinder()
                    .findToDos(userTasks.getTaskList());
            response += ui.toDosToString(toDos);
            break;
        case LIST_TODOS_DONE:
            ArrayList<Task> toDosDone = new TaskFinder()
                    .findToDosDone(userTasks.getTaskList());
            response += ui.toDosDoneToString(toDosDone);
            break;
        case LIST_TODOS_NOT_DONE:
            ArrayList<Task> toDosNotDone = new TaskFinder()
                    .findToDosNotDone(userTasks.getTaskList());
            response += ui.toDosNotDoneToString(toDosNotDone);
            break;
        case LIST_DEADLINES:
            ArrayList<Task> deadlines = new TaskFinder()
                    .findDeadlines(userTasks.getTaskList());
            response += ui.deadlinesToString(deadlines);
            break;
        case LIST_DEADLINES_DONE:
            ArrayList<Task> deadlinesDone = new TaskFinder()
                    .findDeadlinesDone(userTasks.getTaskList());
            response += ui.deadlinesDoneToString(deadlinesDone);
            break;
        case LIST_DEADLINES_NOT_DONE:
            ArrayList<Task> deadlinesNotDone = new TaskFinder()
                    .findDeadlinesNotDone(userTasks.getTaskList());
            response += ui.deadlinesNotDoneToString(deadlinesNotDone);
            break;
        case LIST_EVENTS:
            ArrayList<Task> events = new TaskFinder()
                    .findEvents(userTasks.getTaskList());
            response += ui.eventsToString(events);
            break;
        case LIST_EVENTS_DONE:
            ArrayList<Task> eventsDone = new TaskFinder()
                    .findEventsDone(userTasks.getTaskList());
            response += ui.eventsDoneToString(eventsDone);
            break;
        case LIST_EVENTS_NOT_DONE:
            ArrayList<Task> eventsNotDone = new TaskFinder()
                    .findEventsNotDone(userTasks.getTaskList());
            response += ui.eventsNotDoneToString(eventsNotDone);
            break;
        case LIST_BY_KEYWORD:
            String keyword = input.split("find ")[1];
            ArrayList<Task> filteredTasks = new TaskFinder()
                    .findByKeyword(userTasks.getTaskList(), keyword);
            response += ui.filteredTasksByKeywordToString(filteredTasks, keyword);
            break;
        case DONE:
            int index = Integer.parseInt(input.substring(5)) - 1;

            try {
                if (index >= userTasks.getTaskListSize()) {
                    throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                } else {
                    userTasks.markTaskAsDone(index);
                    response += ui.markAsDoneMessage(userTasks.getTask(index));
                }
                storage.saveToFile(userTasks.getTaskList());
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
            }

            break;
        case DELETE:
            index = Integer.parseInt(input.substring(7)) - 1;

            try {
                if (index >= userTasks.getTaskListSize()) {
                    throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                } else {
                    task = userTasks.getTask(index);
                    userTasks.deleteTask(index);
                    response += ui.taskDeletedMessage(task);
                }
                storage.saveToFile(userTasks.getTaskList());
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
            }

            break;
        case BYE:
            response += new ByeCommand().getResponse();
        break;
        case INVALID_IS_EMPTY:
            try {
                throw new DukeException("", ExceptionType.EMPTY_INPUT);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_COMMAND:
            try {
                throw new DukeException("", ExceptionType.INVALID_COMMAND);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_EMPTY_DESCRIPTION:
            try {
                throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_DEADLINE_NO_BY:
            try {
                throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_EVENT_NO_START_END:
            try {
                throw new DukeException("", ExceptionType.EVENT_NO_START_END);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
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