package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * <h1> Duke Parser class </h1>
 * This class is the class that processes the
 * commands to create readable Tasks that will be stored in
 * the arraylist of tasks and arraylist of records
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-25-08
 */
public class Parser {

    /**
     * Processes the command and filters
     * it the correct private methods to
     * instantiate the Task objects to be recorded
     *
     * @param cmd This is the user input
     * @param taskList This is the taskList class that stores the current lists of tasks
     * @throws DukeException Exception for unidentified commands
     */
    public static String process(String cmd, TaskList taskList, boolean isDone) throws DukeException {
        String[] stringArr = cmd.split(" ");
        String response;
        if (stringArr[0].equals("list")) {
            response = processorList(taskList);
        } else if (stringArr[0].equals("done")) {
            int index = Integer.parseInt(stringArr[1]);
            response = taskList.updateTask(index);
        } else if (stringArr[0].equals("delete")) {
            int index = Integer.parseInt(stringArr[1]);
            response = taskList.deleteTask(index);
        } else if (stringArr[0].equals("find")) {
            response = processorFind(cmd, taskList);
        } else if (stringArr[0].equals("refresh")) {
            response = processorRefresh(taskList);
        } else if (stringArr[0].equals("important")) {
            int index = Integer.parseInt(stringArr[1]);
            response = taskList.setTaskAsImportant(index);
        } else if (stringArr[0].equals("todo")) {
            response = processorAddTodo(cmd, taskList, isDone);
        } else if (stringArr[0].equals("deadline")) {
            response = processorAddDeadline(cmd, taskList, isDone);
        } else if (stringArr[0].equals("event")) {
            response = processorAddEvent(cmd, taskList, isDone);
        } else {
            String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException(message);
        }
        return Ui.showResponse(response, cmd);
    }

    private static String processorRefresh(TaskList taskList) {
        taskList.refreshTasklist();
        return "Records and Tasks refreshed.";
    }

    private static String processorFind(String command, TaskList taskList) {
        int counter = 1;
        try {
            String[] stringarr = command.split(" ", 2);
            String key = stringarr[1];
            if (key.length() == 0) {
                return Ui.showError("Please provide a key");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list: \n");
            for (int i = 0; i < taskList.getListSize(); i++) {
                Task currentTask = taskList.getTask();
                if (currentTask.getTask().contains(key)) {
                    String findResponse = counter + "." + currentTask.toString() + "\n";
                    stringBuilder.append(findResponse);
                    counter++;
                }
            }
            taskList.refillTaskPriorityQueue();
            stringBuilder.append(Ui.showLine());
            return stringBuilder.toString();
        } catch (Exception e) {
            return Ui.showError("Please give a key");
        }
    }

    private static String processorList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.getListSize(); i++) {
            int index = i + 1;
            Task currentTask = taskList.getTask();
            String listResponse = index + "." + currentTask.toString() + "\n";
            stringBuilder.append(listResponse);
        }
        taskList.refillTaskPriorityQueue();
        stringBuilder.append(Ui.showLine());
        return stringBuilder.toString();
    }

    private static String processorAddTodo(String cmd, TaskList taskList, boolean isDone) throws DukeException {
        String[] stringArr = cmd.split(" ", 2);
        Task taskObj;
        if (stringArr.length <= 1) {
            String message = "The description of a Todo cannot be empty";
            throw new DukeException(message);
        }
        taskObj = Todo.createTodo(stringArr[1]);
        if (isDone) {
            taskObj.setDone();
        }
        return taskList.addTask(taskObj);
    }

    private static String processorAddEvent(String cmd, TaskList taskList, boolean isDone) throws DukeException {
        String[] stringArr = cmd.split(" ", 2);
        Task taskObj;
        if (stringArr.length <= 1) {
            String message = "The description of an Event cannot be empty";
            throw new DukeException(message);
        }
        try {
            String[] secondArr = stringArr[1].split("/at", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(secondArr[1].trim(), formatter);
            taskObj = Event.createEvent(secondArr[0], date);
        } catch (DateTimeParseException e) {
            String message = "That does not look like a proper Date. "
                    + "Please input MMM d yyyy HH:mm eg. Aug 25 2020 15:00";
            throw new DukeException(message);
        }
        if (isDone) {
            taskObj.setDone();
        }
        return taskList.addTask(taskObj);
    }

    private static String processorAddDeadline(String cmd, TaskList taskList, boolean isDone) throws DukeException {
        String[] stringArr = cmd.split(" ", 2);
        Task taskObj;
        if (stringArr.length <= 1) {
            String message = "The description of a Deadline cannot be empty";
            throw new DukeException(message);
        }
        try {
            String[] secondArr = stringArr[1].split("/by", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(secondArr[1].trim(), formatter);
            taskObj = Deadline.createDeadline(secondArr[0], date);
        } catch (DateTimeParseException e) {
            String message = "That does not look like a proper Date. "
                    + "Please input MMM d yyyy HH:mm eg. Aug 25 2020 15:00";
            throw new DukeException(message);
        }
        if (isDone) {
            taskObj.setDone();
        }
        return taskList.addTask(taskObj);
    }

    /**
     * Processes the Old Tasks that are scanned from the Text file.
     * @param taskHistory ArrayList of String of Tasks from the Text File
     * @return String
     */
    public static String processOldTasks(ArrayList<String> taskHistory, TaskList taskList) {
        for (int i = 0; i < taskHistory.size(); i++) {
            String oldTask = taskHistory.get(i);
            boolean isDone = oldTask.charAt(4) == 'O';
            try {
                String command = processStoredTask(oldTask);
                Parser.process(command, taskList, isDone);
            } catch (DukeException e) {
                return Ui.showError(e.getMessage());
            }
        }
        return "Total number of Task loaded from previous file: " + taskHistory.size();
    }

    private static String processStoredTask(String task) throws DukeException {
        String[] stringArr = task.split(" ", 2);
        switch (task.charAt(1)) {
        case 'T':
            return "todo " + stringArr[1];
        case 'D':
            String[] secondArr = stringArr[1].split("by: ", 2);
            String storedDeadlineTask = secondArr[0];
            String storedDeadlineDate = secondArr[1];
            return "deadline " + storedDeadlineTask + "/by " + storedDeadlineDate;
        case 'E':
            String[] thirdArr = stringArr[1].split("at: ", 2);
            String storedEventTask = thirdArr[0];
            String storedEventDate = thirdArr[1];
            return "event " + storedEventTask + "/at " + storedEventDate;
        default:
            String errorMessage = "Failed to process Stored Tasks";
            throw new DukeException(errorMessage);
        }
    }
}
