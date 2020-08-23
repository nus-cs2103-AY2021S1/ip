package DukeHelper;
import Commands.Commands;
import Helper.DateTimeHelper;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Exception.DukeInvalidArgumentException;
import Exception.DukeException;

import java.time.LocalDate;

public class Parser {
    public static Todo stringToTodo(String content, boolean isLoaded, boolean isDone) {
        Todo todoTask = new Todo(content);
        if(isLoaded && isDone) todoTask.markAsDone();
        return todoTask;
    }
    public static Deadline stringToDeadline(String content, LocalDate deadline, String exactTime, String deadlineStr, boolean isLoaded, boolean isDone) {
        Deadline deadlineTask = new Deadline(content, deadline, exactTime, deadlineStr);
        if(isLoaded && isDone) deadlineTask.markAsDone();
        return deadlineTask;
    }
    public static Event stringToEvent(String content, LocalDate deadline, String exactTime, String deadlineStr, Boolean isLoaded, Boolean isDone) {
        Event eventTask = new Event(content, deadline, exactTime, deadlineStr);
        if(isLoaded && isDone) eventTask.markAsDone();
        return eventTask;
    }
    public String[] extractData(boolean isLoaded, String[] tokens) {
        String content = "";
        String deadlineStr = "";
        int tokens_limit = isLoaded ? tokens.length - 1 : tokens.length; //saved items has a final token which decides its completion status
        for(int i = 1;i < tokens_limit; i++) {
            if(tokens[i].length() == 0) continue;
            if(tokens[i].charAt(0) == '/') {
                for(int j = i + 1; j < tokens_limit;j++) {
                    deadlineStr += tokens[j] + " ";
                }
                break;
            }
            content += tokens[i] + " ";
        }
        return new String[]{content, deadlineStr};
    }
    public Task parseCommand(Commands commandType, String[] tokens, boolean isLoaded, int numTasks) throws DukeException, DukeInvalidArgumentException{
        String result_prefix = "Got it. I've added this task:\n      ";
        String result_subfix = "Now you have " + (numTasks + 1) + " tasks in the list.";

        String[] extractedData = extractData(isLoaded, tokens);
        String content = extractedData[0];
        String deadlineStr = extractedData[1];
        boolean isDone = tokens[tokens.length - 1].equals("1");
        LocalDate deadline = LocalDate.now();
        String exactTime = "";

        //Error handling
        if(deadlineStr.equals("") && (commandType == Commands.DEADLINE || commandType == Commands.EVENT))
            throw new DukeInvalidArgumentException("NOT ENOUGH INFORMATION!!!");

        if(commandType == Commands.DEADLINE || commandType == Commands.EVENT) {
            DateTimeHelper dtHelper = DateTimeHelper.processDateTime(deadlineStr);
            if (dtHelper != null) {
                deadline = dtHelper.getDate();
                String resTime = dtHelper.processTimeStr();
                if (resTime.equals("success")) exactTime = dtHelper.getTime();
            } else {
                throw new DukeException("Wrong format\n    Your date and time(optional) should be in this format:\n      yyyy-mm-dd HHmm\n    e.g: 2019-10-15 1800 or 2019-10-15");
            }
        }
        content = content.strip();
        if(content.equals("")) throw new DukeException("Description cannot be empty PLEASE!!!");
        switch (commandType) {
            case DEADLINE: {
                Deadline task = stringToDeadline(content, deadline, exactTime, deadlineStr, isLoaded, isDone);
                task.setUiOutput(result_prefix + task.returnStringForm() + "\n    " + result_subfix);
                return task;
            }
            case EVENT: {
                Event task = stringToEvent(content, deadline, exactTime, deadlineStr, isLoaded, isDone);
                task.setUiOutput(result_prefix + task.returnStringForm() + "\n    " + result_subfix);
                return task;
            }
            default: {
                Todo task = stringToTodo(content, isLoaded, isDone);
                task.setUiOutput(result_prefix + task.returnStringForm() + "\n    " + result_subfix);
                return task;
            }
        }
    }
}
