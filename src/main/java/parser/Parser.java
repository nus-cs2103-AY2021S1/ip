package parser;

import tasklist.TaskList;
import tasklist.Todo;
import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import exception.DukeException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Parser class is responsible for handling input commands.
 */
public class Parser {

    /**
     * Handles commands regarding todos, deadlines and events.
     * @param input The command input.
     * @param list The current TaskList.
     * @throws DukeException if there is an error with the input or input is not given in the correct format.
     */
    public static String commandTask(String input, TaskList list) throws DukeException {

        String[] splitString = input.split(" ",2);

        if (splitString[0].equals("todo")) {
            return todoHandler(splitString, list);
        } else if (splitString[0].equals("deadline")) {
            return deadlineHandler(splitString, list);
        } else if (splitString[0].equals("event")) {
            return eventHandler(splitString, list);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handles all non-task related commands(commands that do not begin with a task type).
     * @param input The input command.
     * @param list The current TaskList.
     * @throws DukeException if there is an error with the input or input is not given in the correct format.
     */
    public static String commandParser(String input, TaskList list, TaskList archives) throws DukeException {
        assert !input.equals(""): "Input cannot be empty";

        String[] splitInput = input.split(" ");

        if (splitInput[0].equals("list")) {
            return list.listTasks();
        } else if (splitInput[0].equals("done")) {
            return doneHandler(splitInput, list);
        } else if (splitInput[0].equals("delete")) {
            return deleteHandler(splitInput, list);
        } else if (splitInput[0].equals("find")) {
            return findHandler(input, splitInput, list);
        } else if (splitInput[0].equals("archive")) {
            return archiveHandler(splitInput, list, archives);
        } else if (splitInput[0].equals("bye")) {
            return byeHandler();
        } else {
            try {
                return commandTask(input,list);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }

    public static String archiveHandler(String[] splitInput, TaskList list, TaskList archives) throws DukeException {
        if(splitInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please specify which task to archive");
        } else {
            String taskToArchive = splitInput[1];
            try {
                int taskNumberInt = Integer.parseInt(taskToArchive) - 1;

                if (taskNumberInt + 1 > list.getLength()) {
                    throw new DukeException("☹ OOPS!!! Your task number is out of bounds");
                } else {
                    return list.archiveTask(taskNumberInt, archives);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Invalid task number.");
            }
        }
    }

    public static String byeHandler() {
        return "Bye. Hope to see you again soon!";
    }

    public static String doneHandler(String[] splitInput, TaskList list) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please specify which task is done");
        } else {
            String taskNumberString = splitInput[1];
            try {
                int taskNumberInt = Integer.parseInt(taskNumberString) - 1;

                if (taskNumberInt + 1 > list.getLength()) {
                    throw new DukeException("☹ OOPS!!! Your task number is out of bounds");
                } else {
                    Task t = list.get(taskNumberInt);
                    String text = t.markDone();
                    list.updateDone();
                    return text;
                }
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Invalid task number.");
            }
        }
    }

    public static String deleteHandler(String[] splitInput, TaskList list) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please specify which task you want to delete");
        } else {
            String taskNumberString = splitInput[1];
            try {
                int taskNumberInt = Integer.parseInt(taskNumberString) - 1;

                if (taskNumberInt + 1 > list.getLength()) {
                    throw new DukeException("☹ OOPS!!! Your task number is out of bounds");
                } else {
                    return list.removeTask(taskNumberInt);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Invalid task number.");
            }
        }
    }

    public static String findHandler(String input, String[] splitInput, TaskList list) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please specify the keyword to find");
        } else {
            String[] findArray = input.split(" ", 2);
            String keywords = findArray[1];
            ArrayList<Task> temp = list.findTasks(keywords);
            String text = "Here are the tasks in your list:";

            for (int i = 0; i < temp.size(); i++) {
                int listNumber = i + 1;
                Task currentTask = temp.get(i);

                text += "\n" + listNumber + "." + currentTask.toString();
            }
            return text;
        }
    }

    public static String todoHandler(String[] splitString, TaskList list) throws DukeException {
        if (splitString.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String info = splitString[1];
            Todo newTodo = new Todo(info);
            return list.addTask(newTodo);
        }
    }

    public static String deadlineHandler(String[] splitString, TaskList list) throws DukeException {
        if (splitString.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String info = splitString[1];
            String[] information = info.split(" /by ");
            if (information.length == 1) {
                throw new DukeException("☹ OOPS!!! Please specify the deadline time");
            } else {
                String description = information[0];
                String by = information[1];
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    Deadline newDeadline = new Deadline(description, dateTime);

                    return list.addTask(newDeadline);
                } catch (DateTimeException ex) {
                    return "Please specify the date and time in this format dd/MM/yyyy HH:mm";
                }
            }
        }
    }

    public static String eventHandler(String[] splitString, TaskList list) throws DukeException {
        if (splitString.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            String info = splitString[1];
            String[] information = info.split(" /at ");

            if (information.length == 1) {
                throw new DukeException("☹ OOPS!!! Please state the event time");
            } else {
                String description = information[0];
                String at = information[1];
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
                    Event newEvent = new Event(description, dateTime);

                    return list.addTask(newEvent);
                } catch (DateTimeException ex) {
                    return "Please specify the date and time in this format dd/MM/yyyy HH:mm";
                }
            }
        }
    }
}
