package duke.dukehelper;
import duke.commands.Commands;
import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.helper.DateTimeHelper;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.function.Function;

public class Parser {
    private final static String PARSE_ERROR = "Wrong format\n    Your date and time(optional) "
            + "should be in this format:\n      yyyy-mm-dd HHmm\n    e.g: 2019-10-15 1800 or 2019-10-15";
    private final static String EMPTY_DESC_ERROR = "Description cannot be empty PLEASE!!!";
    private final static String MISSING_INFO_ERROR = "NOT ENOUGH INFORMATION!!!";
    private final static String NO_TIME = "";

    private DateTimeHelper dtHelper;

    /**
     * Converts string to Todo
     * @param content
     * @param isLoaded
     * @param isDone
     * @return
     */
    public static Todo stringToTodo(String content, boolean isLoaded, boolean isDone) {
        assert (content != null && !content.equals("")) : "Empty or null content";

        Todo todoTask = new Todo(content);
        if (isLoaded && isDone) {
            todoTask.markAsDone();
        }
        return todoTask;
    }


    /**
     * Converts string to Deadline
     * @param content
     * @param deadline
     * @param exactTime
     * @param deadlineStr
     * @param isLoaded
     * @param isDone
     * @return
     */
    public static Deadline stringToDeadline(String content, LocalDate deadline,
                                            String exactTime, String deadlineStr,
                                            boolean isLoaded, boolean isDone) {
        assert (content != null && !content.equals("")) : "Empty or null content";
        assert (deadline != null) : "Null deadline";
        assert (deadlineStr != null && !deadlineStr.equals("")) : "Empty or null deadline string";

        Deadline deadlineTask = new Deadline(content, deadline, exactTime, deadlineStr);
        if (isLoaded && isDone) {
            deadlineTask.markAsDone();
        }
        return deadlineTask;
    }

    /**
     * Converts string to Event
     * @param content
     * @param deadline
     * @param exactTime
     * @param deadlineStr
     * @param isLoaded
     * @param isDone
     * @return
     */
    public static Event stringToEvent(String content, LocalDate deadline,
                                      String exactTime, String deadlineStr,
                                      Boolean isLoaded, Boolean isDone) {
        assert (content != null && !content.equals("")) : "Empty or null content for event";
        assert (deadline != null) : "Null deadline for event";
        assert (deadlineStr != null && !deadlineStr.equals("")) : "Empty or null deadline string for event";

        Event eventTask = new Event(content, deadline, exactTime, deadlineStr);
        if (isLoaded && isDone) {
            eventTask.markAsDone();
        }
        return eventTask;
    }

    /**
     * Extracts content and deadline from tokens
     * @param isLoaded
     * @param tokens
     * @return content and deadline as strings
     */
    public String[] extractData(boolean isLoaded, String[] tokens) {
        assert (tokens != null) : "Null tokens";

        String content = "";
        String deadlineStr = "";
        //saved items has a final token which decides its completion status
        int tokensLimit = isLoaded ? tokens.length - 1 : tokens.length;
        for (int i = 1; i < tokensLimit; i++) {
            if (tokens[i].length() == 0) {
                continue;
            }
            if (tokens[i].charAt(0) == '/') {
                for (int j = i + 1; j < tokensLimit; j++) {
                    deadlineStr += tokens[j] + " ";
                }
                break;
            }
            content += tokens[i] + " ";
        }
        return new String[]{content, deadlineStr};
    }

    private String extractTime() {
        String resTime = dtHelper.processTimeStr();
        if (resTime.equals("success")) {
            return dtHelper.getTime();
        }
        return NO_TIME;
    }

    private LocalDate extractDate() throws DukeException {
        if (dtHelper != null) {
            return dtHelper.getDate();
        } else {
            throw new DukeException(PARSE_ERROR);
        }
    }

    /**
     * Classifies the task based on command type
     * @param commandType
     * @param content
     * @param numTasks
     * @param deadline
     * @param exactTime
     * @param deadlineStr to be stored
     * @param isLoaded
     * @param isDone
     * @return classified task
     */
    private Task classifyTasks(Commands commandType, String content, int numTasks,
                               LocalDate deadline, String exactTime, String deadlineStr,
                               boolean isLoaded, boolean isDone ) {
        String resultPrefix = "Got it. I've added this task:\n      ";
        String resultSubfix = "Now you have " + (numTasks + 1) + " tasks in the list.";

        switch (commandType) {
            case DEADLINE: {
                Deadline task = stringToDeadline(content, deadline, exactTime, deadlineStr, isLoaded, isDone);
                task.setUiOutput(resultPrefix + task.returnStringForm() + "\n    " + resultSubfix);
                return task;
            }
            case EVENT: {
                Event task = stringToEvent(content, deadline, exactTime, deadlineStr, isLoaded, isDone);
                task.setUiOutput(resultPrefix + task.returnStringForm() + "\n    " + resultSubfix);
                return task;
            }
            default: {
                Todo task = stringToTodo(content, isLoaded, isDone);
                task.setUiOutput(resultPrefix + task.returnStringForm() + "\n    " + resultSubfix);
                return task;
            }
        }
    }

    /**
     * Parses operational commands
     * @param commandType
     * @param markNumber
     * @param numTasks
     * @param tasks
     * @param saveTaskListStorage
     * @return string representation of operational commands
     * @throws DukeException
     */
    public String parseOperationCommand(Commands commandType, int markNumber, int numTasks,
                                        TaskList tasks, Function<Void, Void> saveTaskListStorage) throws DukeException {
        String result;
        switch (commandType) {
            case DONE: {
                result = tasks.doneTask(markNumber);
                break;
            }
            case DELETE: {
                result = tasks.deleteTask(markNumber, numTasks - 1);
                break;
            }
            default: {
                result = "";
            }
        }
        saveTaskListStorage.apply(null);
        return result;
    }

    /**
     * Parses task strings
     * @param commandType
     * @param tokens
     * @param isLoaded
     * @param numTasks
     * @return parsed tasks
     * @throws DukeException
     * @throws DukeInvalidArgumentException
     */
    public Task parseTaskCommand(Commands commandType, String[] tokens, boolean isLoaded,
                             int numTasks) throws DukeException, DukeInvalidArgumentException {
        String[] extractedData = extractData(isLoaded, tokens);
        String content = extractedData[0];
        String deadlineStr = extractedData[1];
        boolean isDone = tokens[tokens.length - 1].equals("1");

        //init
        LocalDate deadline = LocalDate.now();
        String exactTime = "";

        if (deadlineStr.equals("") && (commandType == Commands.DEADLINE || commandType == Commands.EVENT)) {
            throw new DukeInvalidArgumentException(MISSING_INFO_ERROR);
        }
        if (commandType == Commands.DEADLINE || commandType == Commands.EVENT) {
            dtHelper = DateTimeHelper.processDateTime(deadlineStr);
            deadline = extractDate();
            exactTime = extractTime();
        }
        content = content.strip();
        if (content.equals("")) {
            throw new DukeException(EMPTY_DESC_ERROR);
        }

        return classifyTasks(commandType, content, numTasks, deadline, exactTime, deadlineStr, isLoaded, isDone);
    }
}
