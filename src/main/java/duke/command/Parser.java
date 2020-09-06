package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.storage.Storage;
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
     * @param command This is the user input
     * @param taskList This is the taskList class that stores the current lists of tasks
     * @param storage This is the Storage object that records the tasks
     * @throws DukeException Exception for unidentified commands
     */
    public static String process(String command, TaskList taskList, Storage storage) throws DukeException {
        String[] stringarr = command.split(" ");
        String finalString;
        if (stringarr[0].equals("list")) {
            String response = processorList(taskList);
            finalString = Ui.showCommandMessage(response);
        } else if (stringarr[0].equals("done")) {
            int index = Integer.parseInt(stringarr[1]);
            String response = taskList.updateTask(index);
            storage.updateRecord(response, index);
            finalString = Ui.showResponse(response, command);
        } else if (stringarr[0].equals("delete")) {
            int index = Integer.parseInt(stringarr[1]);
            String response = taskList.deleteTask(index);
            storage.deleteRecord(index);
            finalString = Ui.showResponse(response, command);
        } else if (stringarr[0].equals("find")) {
            String response = processorFind(command, taskList);
            finalString = Ui.showCommandMessage(response);
        } else if (stringarr[0].equals("refresh")) {
            String response = processorRefresh(taskList, storage);
            finalString = Ui.showCommandMessage(response);
        } else {
            String response = processorAdd(command, taskList, false);
            storage.saveRecord(response);
            finalString = Ui.showResponse(response, command);
        }
        return finalString;
    }

    /**
     * Overloaded method that processes tasks that are formatted and saved in the textfile
     * @param command command passed for processing
     * @param taskList the tasklist that stores the tasks
     * @throws DukeException for invalid commands
     */
    public static void process(String command, TaskList taskList, boolean isDone) throws DukeException {
        processorAdd(command, taskList, isDone);
    }

    private static String processorRefresh(TaskList taskList, Storage storage) {
        taskList.refreshTasklist();
        storage.refreshRecords();
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
                if (taskList.getTask(i).getTask().contains(key)) {
                    String findResponse = counter + "." + taskList.getTask(i).toString() + "\n";
                    stringBuilder.append(findResponse);
                    counter++;
                }
            }
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
            String listResponse = index + "." + taskList.getTask(i).toString() + "\n";
            stringBuilder.append(listResponse);
        }
        stringBuilder.append(Ui.showLine());
        return stringBuilder.toString();
    }

    private static String processorAdd(String cmd, TaskList taskList, boolean isDone) throws DukeException {
        String[] stringarr = cmd.split(" ", 2);
        Task taskObj = null;
        if (stringarr[0].equals("todo")) {
            if (stringarr.length <= 1) {
                String message = "The description of a Todo cannot be empty";
                throw new DukeException(message);
            } else {
                Todo todo = Todo.createTodo(stringarr[1]);
                taskObj = todo;
            }
        } else if (stringarr[0].equals("deadline")) {
            if (stringarr.length <= 1) {
                String message = "The description of a Deadline cannot be empty";
                throw new DukeException(message);
            } else {
                try {
                    String[] secondarr = stringarr[1].split("/by", 2);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    LocalDateTime date = LocalDateTime.parse(secondarr[1].trim(), formatter);
                    Deadline deadline = Deadline.createDeadline(secondarr[0], date);
                    taskObj = deadline;
                } catch (DateTimeParseException e) {
                    String message = "That does not look like a proper Date. Please input YYYY-MM-DD";
                    throw new DukeException(message);
                }
            }
        } else if (stringarr[0].equals("event")) {
            if (stringarr.length <= 1) {
                String message = "The description of an Event cannot be empty";
                throw new DukeException(message);
            } else {
                String[] secondarr = stringarr[1].split("/at", 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime date = LocalDateTime.parse(secondarr[1].trim(), formatter);
                Event event = Event.createEvent(secondarr[0], date);
                taskObj = event;
            }
        } else {
            String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException(message);
        }
        taskList.addTask(taskObj);
        if (isDone) {
            taskObj.setDone();
        }
        return taskObj.toString();
    }

    /**
     *
     * @param taskHistory
     * @return
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
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime deadlineDate = LocalDateTime.parse(secondArr[1], deadlineFormatter);
            return "deadline " + secondArr[0] + "/by " + deadlineDate;
        case 'E':
            String[] thirdArr = stringArr[1].split("at: ", 2);
            System.out.println(thirdArr[1]);
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            LocalDateTime eventDate = LocalDateTime.parse(thirdArr[1].trim(), eventFormatter);
            return "event " + thirdArr[0] + "/at " + eventDate;
        default:
            String errorMessage = "Failed to process Stored Tasks";
            throw new DukeException(errorMessage);
        }
    }
}
