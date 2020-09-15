package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import duke.exceptions.CommandNotFoundException;
import duke.exceptions.DateFormatException;
import duke.exceptions.DurationFormatException;
import duke.exceptions.LackOfTimeException;
import duke.exceptions.NullCommandContentException;
import duke.exceptions.NullCommandException;
import duke.exceptions.TaskNotSpecifyException;
import duke.exceptions.TaskOutOfBoundException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.SingletonTaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Printer;
import duke.utils.Constants;
import duke.utils.UtilFunction;


/**
 * The class to match the command to Duke's response.
 */
public class DukeCommandMatcher {

    private static final List<String> commandList = new ArrayList<>(Arrays.asList(Constants.LISTPATTERN,
            Constants.EXITPATTERN, Constants.DONEPATTERN, Constants.TODOPATTERN, Constants.DEADLINEPATTERN,
            Constants.EVENTPATTERN, Constants.DELETEPATTERN, Constants.FINDPATTERN, Constants.HELPPATTERN
    ));

    private SingletonTaskList taskList;

    public DukeCommandMatcher(Storage database) {
        this.taskList = SingletonTaskList.getInstance(database);
    }

    /**
     * Match the command to corresponding behavior.
     * @param command
     * @return message of implementation
     * @throws CommandNotFoundException when command is not found
     * @throws NullCommandException when the command is empty
     * @throws LackOfTimeException when event/deadline command lacks time
     * @throws NullCommandContentException when todo/event/deadline lacks content
     * @throws TaskOutOfBoundException when done/delete is implemented on task that does not exist
     * @throws TaskNotSpecifyException when the task to be done/deleted is not specified
     * @throws DateFormatException when input date format is not in the standard format
     */
    public String handleCommand(String command) throws CommandNotFoundException, NullCommandException,
            LackOfTimeException, NullCommandContentException, TaskOutOfBoundException, TaskNotSpecifyException,
            DateFormatException, DurationFormatException {
        if (Objects.equals(command, "")) {
            throw new NullCommandException(command);
        }

        //get the first command
        String[] splitCommand = command.split("\\s+", 2);
        //check if the command is in the list
        for (String commandPattern: commandList) {
            //the command is in the list
            if (UtilFunction.matchPattern(commandPattern, splitCommand[0])) {
                switch (commandPattern) {
                case Constants.EXITPATTERN:
                    return handleExit();
                case Constants.LISTPATTERN:
                    return handleList();
                case Constants.DONEPATTERN:
                    return handleDone(splitCommand);
                case Constants.TODOPATTERN:
                    return handleTodo(splitCommand);
                case Constants.DEADLINEPATTERN:
                    return handleDeadline(splitCommand);
                case Constants.EVENTPATTERN:
                    return handleEvent(splitCommand);
                case Constants.DELETEPATTERN:
                    return handleDelete(splitCommand);
                case Constants.FINDPATTERN:
                    return handleFind(splitCommand);
                case Constants.HELPPATTERN:
                    return handleHelp();
                default:
                    break;
                }
            }
        }
        throw new CommandNotFoundException(command);
    }

    private String handleExit() {
        Printer.printBye();
        return Constants.EXITRESPONSE;
    }

    private String handleAdd(Task task) {
        return taskList.add(task);
    }

    private String handleList() {
        return taskList.listAll();
    }

    private String handleDone(String[] targetTask) throws TaskOutOfBoundException, TaskNotSpecifyException {
        try {
            int targetTaskPos = Integer.parseInt(targetTask[1]) - 1;
            return taskList.setTaskDone(targetTaskPos);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotSpecifyException("task to be done not specified", "DONE");
        }
    }

    private String handleTodo(String[] todoStr) throws NullCommandContentException {
        ToDo todo;
        try {
            String todoContent = todoStr[1];
            todo = new ToDo(todoContent);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new NullCommandContentException("Description cannot be null", "Todo");
        }
        return handleAdd(todo);
    }

    private String handleDeadline(String[] deadlineStr) throws NullCommandContentException, LackOfTimeException,
            DateFormatException {
        Deadline deadline;
        String[] splitDeadline;
        try {
            String deadlineContent = deadlineStr[1];
            splitDeadline = deadlineContent.split("/", 2);
        } catch (IndexOutOfBoundsException e) {
            throw new NullCommandContentException("Description cannot be null", "Deadline");
        }

        try {
            String standardDate = UtilFunction.formatDateTimeToStandard(splitDeadline[1]);
            deadline = new Deadline(splitDeadline[0], standardDate);
        } catch (IndexOutOfBoundsException e) {
            throw new LackOfTimeException("The time cannot be empty", "Duke.Deadline");
        }
        return handleAdd(deadline);
    }

    private String handleEvent(String[] eventStr) throws NullCommandContentException, LackOfTimeException,
            DateFormatException, DurationFormatException {
        Event event;
        String[] splitEventStr;
        try {
            String eventContent = eventStr[1];
            splitEventStr = eventContent.split("/", 2);
            if (eventContent.trim().isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NullCommandContentException("Description cannot be null", "Event");
        }
        try {
            String standardDate = UtilFunction.formatDurationToStandard(splitEventStr[1]);
            event = new Event(splitEventStr[0], standardDate);
        } catch (IndexOutOfBoundsException e) {
            throw new LackOfTimeException("The time cannot be empty", "Event");
        }
        return handleAdd(event);
    }

    private String handleDelete(String[] deleteStr) throws TaskNotSpecifyException, TaskOutOfBoundException {
        int taskToDelete;
        try {
            taskToDelete = Integer.parseInt(deleteStr[1]);
            return taskList.delete(taskToDelete);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new TaskNotSpecifyException("task to deletion not specified", "DELETE");
        }
    }

    private String handleFind(String[] findStr) throws NullCommandContentException {
        try {
            String queryKey = findStr[1];
            return taskList.query(queryKey);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NullCommandContentException("no query body", "FIND");
        }
    }

    private String handleHelp() {
        return Printer.printListCommands();
    }

}
