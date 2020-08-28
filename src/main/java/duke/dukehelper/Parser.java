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

public class Parser {
    /**
     * Converts string to Todo
     * @param content
     * @param isLoaded
     * @param isDone
     * @return
     */
    public static Todo stringToTodo(String content, boolean isLoaded, boolean isDone) {
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
    public Task parseCommand(Commands commandType, String[] tokens, boolean isLoaded,
                             int numTasks) throws DukeException, DukeInvalidArgumentException {
        String resultPrefix = "Got it. I've added this task:\n      ";
        String resultSubfix = "Now you have " + (numTasks + 1) + " tasks in the list.";

        String[] extractedData = extractData(isLoaded, tokens);
        String content = extractedData[0];
        String deadlineStr = extractedData[1];
        boolean isDone = tokens[tokens.length - 1].equals("1");
        LocalDate deadline = LocalDate.now();
        String exactTime = "";

        //Error handling
        if (deadlineStr.equals("") && (commandType == Commands.DEADLINE || commandType == Commands.EVENT)) {
            throw new DukeInvalidArgumentException("NOT ENOUGH INFORMATION!!!");
        }
        if (commandType == Commands.DEADLINE || commandType == Commands.EVENT) {
            DateTimeHelper dtHelper = DateTimeHelper.processDateTime(deadlineStr);
            if (dtHelper != null) {
                deadline = dtHelper.getDate();
                String resTime = dtHelper.processTimeStr();
                if (resTime.equals("success")) {
                    exactTime = dtHelper.getTime();
                }
            } else {
                throw new DukeException("Wrong format\n    Your date and time(optional) "
                        + "should be in this format:\n      yyyy-mm-dd HHmm\n    e.g: 2019-10-15 1800 or 2019-10-15");
            }
        }
        content = content.strip();
        if (content.equals("")) {
            throw new DukeException("Description cannot be empty PLEASE!!!");
        }
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
}
