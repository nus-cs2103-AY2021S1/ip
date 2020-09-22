package duke;

import duke.exception.DeleteOutOfRangeException;
import duke.exception.DoneOutOfRangeException;
import duke.exception.EmptyDeadlineException;
import duke.exception.EmptyEventException;
import duke.exception.EmptyTodoException;
import duke.exception.MissingDeadlineDateException;
import duke.exception.MissingDeleteArgumentException;
import duke.exception.MissingDoneArgumentException;
import duke.exception.MissingEventDateException;
import duke.exception.MissingFindArgumentException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Static class that parses user inputs and executes them.
 */
public class Parser {

    /**
     * Parses and executes user input, adding tasks to given TaskList and output
     * messages through given Ui.
     *
     * @param input A String of user input.
     * @param tasks A TaskList object that the tasks will be modified in.
     * @param ui    An Ui object that will display the outputs and collect user inputs.
     * @return A Boolean of whether TaskList was updated.
     * @throws MissingDoneArgumentException   If done was input without an argument.
     * @throws DoneOutOfRangeException        If done was input with an argument out of range.
     * @throws MissingDeleteArgumentException If delete was input without an argument.
     * @throws DeleteOutOfRangeException      If delete was input with an argument out of range.
     * @throws EmptyTodoException             If todo was input without a description.
     * @throws MissingDeadlineDateException   If deadline was input without a date.
     * @throws EmptyDeadlineException         If deadline was input without a description.
     * @throws MissingEventDateException      If event was input without a date.
     * @throws EmptyEventException            If event was input without a description.
     * @throws UnknownCommandException        If input is not recognised by Duke.
     * @throws MissingFindArgumentException   If find was input without a keyword.
     */

    public static ParseInfo parseAndExecute(String input, TaskList tasks, Ui ui) throws MissingDoneArgumentException,
            DoneOutOfRangeException, MissingDeleteArgumentException, DeleteOutOfRangeException, EmptyTodoException,
            MissingDeadlineDateException, EmptyDeadlineException, MissingEventDateException, EmptyEventException,
            UnknownCommandException, MissingFindArgumentException {
        ParseInfo returnable = new ParseInfo();
        CommandType commandType = parseCommandType(input);
        switch (commandType) {
        case DONE: {
            int index = parseDone(input, tasks.getCount());
            ui.sendMarkedAsDoneMessage(
                    tasks.markTaskAsDone(index) //checkthis
            );
            returnable.taskListDidUpdate();
            break;
        }
        case DELETE: {
            int index = parseDelete(input, tasks.getCount());
            ui.sendDeleteTaskMessage(
                    tasks.deleteTask(index) //checkthis
            );
            ui.sendCount(tasks);
            returnable.taskListDidUpdate();
            break;
        }
        case TODO: {
            ToDo taskToAdd = parseToDo(input);
            ui.sendAddTaskMessage(
                    tasks.addTask(taskToAdd)
            );
            ui.sendCount(tasks);
            returnable.taskListDidUpdate();
            break;
        }
        case DEADLINE: {
            Deadline taskToAdd = parseDeadline(input);
            ui.sendAddTaskMessage(
                    tasks.addTask(taskToAdd)
            );
            ui.sendCount(tasks);
            returnable.taskListDidUpdate();
            break;
        }
        case EVENT: {
            Event taskToAdd = parseEvent(input);
            ui.sendAddTaskMessage(
                    tasks.addTask(taskToAdd)
            );
            ui.sendCount(tasks);
            returnable.taskListDidUpdate();
            break;
        }
        case LIST: {
            ui.listTasks(tasks);
            //no update
            break;
        }
        case FIND: {
            String keyword = parseFind(input);
            ui.findTasks(tasks, keyword);
            //no update
            break;
        }
        case UNDO: {
            //no update
            returnable.taskListWasUndone();
            break;
        }
        default:
            throw new UnknownCommandException();
        }
        return returnable;
    }

    /**
     * Parses and executes user input and provides Duke's output message
     *
     * @param input A String of user input.
     * @param tasks A TaskList object that the tasks will be modified in.
     * @param ui    An Ui object that will display the outputs and collect user inputs.
     * @return A Boolean of whether TaskList was updated.
     * @throws MissingDoneArgumentException   If done was input without an argument.
     * @throws DoneOutOfRangeException        If done was input with an argument out of range.
     * @throws MissingDeleteArgumentException If delete was input without an argument.
     * @throws DeleteOutOfRangeException      If delete was input with an argument out of range.
     * @throws EmptyTodoException             If todo was input without a description.
     * @throws MissingDeadlineDateException   If deadline was input without a date.
     * @throws EmptyDeadlineException         If deadline was input without a description.
     * @throws MissingEventDateException      If event was input without a date.
     * @throws EmptyEventException            If event was input without a description.
     * @throws UnknownCommandException        If input is not recognised by Duke.
     * @throws MissingFindArgumentException   If find was input without a keyword.
     */
    public static ParseInfo parseAndExecuteAndGetMessage(String input, TaskList tasks, Ui ui)
            throws MissingDoneArgumentException, DoneOutOfRangeException, MissingDeleteArgumentException,
            DeleteOutOfRangeException, EmptyTodoException, MissingDeadlineDateException, EmptyDeadlineException,
            MissingEventDateException, EmptyEventException, UnknownCommandException, MissingFindArgumentException {

        assert input.length() != 0 : "enter was registered";
        ParseInfo returnable = new ParseInfo();
        CommandType commandType = parseCommandType(input);
        switch (commandType) {
        case DONE: {
            int index = parseDone(input, tasks.getCount());
            returnable.addResponse(
                    ui.getMarkedAsDoneMessage(
                            tasks.markTaskAsDone(index) //checkthis
                    )
            );
            returnable.taskListDidUpdate();
            break;
        }
        case DELETE: {
            int index = parseDelete(input, tasks.getCount());
            returnable.addResponse(
                    ui.getDeleteTaskMessage(
                            tasks.deleteTask(index)
                    )
            );
            returnable.addResponse(
                    ui.getCountMessage(tasks)
            );
            returnable.taskListDidUpdate();
            break;
        }
        case TODO: {
            ToDo taskToAdd = parseToDo(input);
            returnable.addResponse(
                    ui.getAddTaskMessage(
                            tasks.addTask(taskToAdd)
                    )
            );
            returnable.addResponse(
                    ui.getCountMessage(tasks)
            );
            returnable.taskListDidUpdate();
            break;
        }
        case DEADLINE: {
            Deadline taskToAdd = parseDeadline(input);
            returnable.addResponse(
                    ui.getAddTaskMessage(
                            tasks.addTask(taskToAdd)
                    )
            );
            returnable.addResponse(
                    ui.getCountMessage(tasks)
            );
            returnable.taskListDidUpdate();
            break;
        }
        case EVENT: {
            Event taskToAdd = parseEvent(input);
            returnable.addResponse(
                    ui.getAddTaskMessage(
                            tasks.addTask(taskToAdd)
                    )
            );
            returnable.addResponse(
                    ui.getCountMessage(tasks)
            );
            returnable.taskListDidUpdate();
            break;
        }
        case LIST: {
            returnable.addResponse(
                    ui.getTaskList(tasks)
            );
            //task list did not update, did not change returnable
            break;
        }
        case FIND: {
            String keyword = parseFind(input);
            returnable.addResponse(
                    ui.getFoundTasks(tasks, keyword)
            );
            //task list did not update, did not change returnable
            break;
        }
        case UNDO: {
            returnable.taskListWasUndone();
            break;
        }
        default:
            throw new UnknownCommandException();
        }
        return returnable;
    }

    /**
     * Parses input into a type of command.
     *
     * @param input Given command.
     * @return CommandType of given input.
     */
    public static CommandType parseCommandType(String input) {
        if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
            return CommandType.DONE;
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            return CommandType.DELETE;
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            return CommandType.TODO;
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            return CommandType.EVENT;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.length() >= 4 && input.substring(0, 4).equals("find")) {
            return CommandType.FIND;
        } else if (input.equals("undo")) {
            return CommandType.UNDO;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Parses done and retrieves the index of the task to be marked as done.
     *
     * @param input     Given command.
     * @param taskCount Number of tasks in the task list.
     * @return Index of the task to be marked as done.
     * @throws MissingDoneArgumentException   If done was input without an argument.
     * @throws DoneOutOfRangeException        If done was input with an argument out of range.
     */
    public static int parseDone(String input, int taskCount) throws MissingDoneArgumentException,
            DoneOutOfRangeException {
        if (input.length() <= 5) {
            throw new MissingDoneArgumentException();
        }
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= taskCount || index < 0) {
            throw new DoneOutOfRangeException();
        }
        return index;
    }

    /**
     * Parses delete and retrieves the index of the task to be deleted.
     *
     * @param input     Given command.
     * @param taskCount Number of tasks in the task list.
     * @return Index of the task to be deleted.
     * @throws MissingDeleteArgumentException If delete was input without an argument.
     * @throws DeleteOutOfRangeException      If delete was input with an argument out of range.
     */
    public static int parseDelete(String input, int taskCount) throws MissingDeleteArgumentException,
            DeleteOutOfRangeException {
        if (input.length() <= 7) {
            throw new MissingDeleteArgumentException();
        }
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= taskCount || index < 0) {
            throw new DeleteOutOfRangeException();
        }
        return index;
    }

    /**
     * Parses ToDo and returns the ToDo task created.
     *
     * @param input Given command.
     * @return ToDo task created.
     * @throws EmptyTodoException             If todo was input without a description.
     */
    public static ToDo parseToDo(String input) throws EmptyTodoException {
        if (input.length() == 4) {
            throw new EmptyTodoException();
        }
        String description = input.substring(5);
        if (description.length() == 0) {
            throw new EmptyTodoException();
        }
        ToDo taskToAdd = new ToDo(description);
        return taskToAdd;
    }

    /**
     * Parses Deadline and returns the Deadline task created.
     *
     * @param input Given command.
     * @return Deadline task created
     * @throws EmptyDeadlineException         If deadline was input without a description.
     * @throws MissingEventDateException      If event was input without a date.
     */
    public static Deadline parseDeadline(String input) throws MissingDeadlineDateException, EmptyDeadlineException {
        int index = input.indexOf("/");
        if (index == -1) {
            throw new MissingDeadlineDateException();
        }
        if (input.length() == 8 || input.indexOf("/") <= 9) {
            throw new EmptyDeadlineException();
        }
        String description = input.substring(9, index - 1);
        if (description.length() == 0) {
            throw new EmptyDeadlineException();
        }
        String date = input.substring(index + 4);
        if (date.length() == 0) {
            throw new MissingDeadlineDateException();
        }
        Deadline taskToAdd = new Deadline(description, date);
        return taskToAdd;
    }

    /**
     * Parses Event and returns the Event task created.
     *
     * @param input Given command.
     * @return Event task created.
     * @throws MissingEventDateException      If event was input without a date.
     * @throws EmptyEventException            If event was input without a description.
     */
    public static Event parseEvent(String input) throws MissingEventDateException, EmptyEventException {
        int index = input.indexOf("/");
        if (index == -1) {
            throw new MissingEventDateException();
        }
        if (input.length() == 5 || input.indexOf("/") <= 6) {
            throw new EmptyEventException();
        }
        String description = input.substring(6, index - 1);
        if (description.length() == 0) {
            throw new EmptyEventException();
        }
        String date = input.substring(index + 4);
        if (date.length() == 0) {
            throw new MissingEventDateException();
        }
        Event taskToAdd = new Event(description, date);
        return taskToAdd;
    }

    /**
     * Parses Find and returns the keyword to query.
     *
     * @param input Given command.
     * @return Keyword to query
     * @throws MissingFindArgumentException   If find was input without a keyword.
     */
    public static String parseFind(String input) throws MissingFindArgumentException {
        if (input.length() <= 5) {
            throw new MissingFindArgumentException();
        }
        String keyword = input.substring(5);
        return keyword;
    }

    /**
     * Parses ToDo from save file and returns the task.
     *
     * @param line ToDo in plaintext.
     * @return ToDo object
     */
    public static ToDo parseToDoFromSave(String line) {
        String description = line.substring(8);
        boolean isDone = line.charAt(4) == '1';
        ToDo taskToAdd = new ToDo(description, isDone);
        return taskToAdd;
    }

    /**
     * Parses Deadline from save file and returns the task.
     *
     * @param line Deadline in plaintext.
     * @return Deadline object
     */
    public static Deadline parseDeadlineFromSave(String line) {
        String descriptionAndDeadline = line.substring(8);
        boolean isDone = line.charAt(4) == '1';
        int stringBreak = descriptionAndDeadline.indexOf('|');
        String deadline = descriptionAndDeadline.substring(stringBreak + 2);
        String description = descriptionAndDeadline.substring(0, stringBreak - 1);
        Deadline taskToAdd = new Deadline(description, deadline, isDone);
        return taskToAdd;
    }

    /**
     * Parses Event from save file and returns the task.
     *
     * @param line Event in plaintext.
     * @return Event object
     */
    public static Event parseEventFromSave(String line) {
        String descriptionAndDate = line.substring(8);
        boolean isDone = line.charAt(4) == '1';
        int stringBreak = descriptionAndDate.indexOf('|');
        String date = descriptionAndDate.substring(stringBreak + 2);
        String description = descriptionAndDate.substring(0, stringBreak - 1);
        Event taskToAdd = new Event(description, date, isDone);
        return taskToAdd;
    }
}
