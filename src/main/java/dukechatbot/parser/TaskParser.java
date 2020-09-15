package dukechatbot.parser;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import dukechatbot.constant.DukeConstants;
import dukechatbot.enums.TaskEnum;
import dukechatbot.task.DeadlineTask;
import dukechatbot.task.EventTask;
import dukechatbot.task.Task;
import dukechatbot.task.ToDoTask;

/**
 * outputs the appropriate task given the taskType.
 * If the title is invalid, output appropriate message to Duke.
 */
public class TaskParser {

    /**
     * Parses title and taskType to form Task object.
     *
     * @param title
     * @param taskType
     * @return Task object.
     * @throws IndexOutOfBoundsException
     */
    public static Task parseTaskFromDuke(String title, TaskEnum taskType) throws 
            IndexOutOfBoundsException, NoSuchElementException {
        Task task;
        switch(taskType) {
        case TODO:
            task = parseTodoTask(title);
            break;
        case DEADLINE:
            task = parseDeadlineTask(title);
            break;
        case EVENT:
            task = parseEventTask(title);
            break;
        default:
            throw new NoSuchElementException("\u2639 OOPS!!! task type is invalid.");
        }
        return task;
    }

    private static Task parseTodoTask(String title) {
        return new ToDoTask(title);
    }

    private static Task parseDeadlineTask(String title) {
        try {
            String[] titleComponents = title.split(DukeConstants.DEADLINE_TASK_KEYWORD, 2);
            return new DeadlineTask(titleComponents[0].trim(), titleComponents[1].trim());
        } catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException(
                    "\u2639 OOPS!!! The date and time of a deadline cannot be empty.");
        } catch (DateTimeParseException exception) {
            throw new IndexOutOfBoundsException(
                    "\u2639 OOPS!!! Both date and time (24 hour format) must be "
                            + "in the form \"DD/MM/YYYY HH:MM\".");
        }
    }

    private static Task parseEventTask(String title) {
        try {
            String[] titleComponents = title.split(DukeConstants.EVENT_TASK_KEYWORD, 2);
            String[] dateAndTime = titleComponents[1].trim().split("\\s+", 2);
            String[] times = dateAndTime[1].split("-", 2);
            return new EventTask(titleComponents[0].trim(),
                    dateAndTime[0].trim(), times[0].trim(), times[1].trim());
        } catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException(
                    "\u2639 OOPS!!! The date, start and end time of an event cannot be empty.\n"
                            + DukeConstants.INDENT 
                            + "They must be in the form \"DD/MM/YYYY HH:MM-HH:MM\".");
        }
    }
    
    public static Task parseTaskFromDisk(String[] components) throws NoSuchElementException {
        boolean isDone = Integer.parseInt(components[1]) == 1;
        switch(components[0]) {
        case "T":
            return new ToDoTask(components[2], isDone);
        case "D":
            return new DeadlineTask(components[2], components[3], isDone);
        case "E":
            String[] dateTimeComp = components[3].trim().split("\\s+", 2);
            String date = dateTimeComp[0];
            String[] timeComp = dateTimeComp[1].split("-", 2);
            String startTime = timeComp[0];
            String endTime = timeComp[1];
            return new EventTask(components[2], date, startTime, endTime, isDone);
        default:
            throw new NoSuchElementException();
        }
    }
}
