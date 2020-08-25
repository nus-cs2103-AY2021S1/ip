package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import seedu.duke.commands.*;
import seedu.duke.todo.*;

public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public static Command parse(String str) throws DukeException {
        String[] split1 = str.split(" ", 2);
        String commandWord = split1[0].toLowerCase();
        if (split1.length < 2 || split1[1].equals("")) {
            // one word command
            if (commandWord.equals("list")) {
                return new ListCommand();
            } else if (commandWord.equals("bye")) {
                return new ExitCommand();
            } else if (commandWord.equals("done")) {
                throw new DukeException("Please tell me the task number that you have completed.");
            } else if (commandWord.equals("date")) {
                throw new DukeException("Please provide a date.");
            } else if (commandWord.equals("delete")) {
                throw new DukeException("Please tell me the task number that you want to delete.");
            } else if (commandWord.equals("todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else if (commandWord.equals("deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (commandWord.equals("event")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else {
            // more than one word command
            String commandBody = split1[1].trim();
            if (commandWord.equals("done")) {
                // create done command: "done 1"
                return new DoneCommand(Integer.parseInt(commandBody));
            } else if (commandWord.equals("delete")) {
                // delete 2
                return new DeleteCommand(Integer.parseInt(commandBody));
            } else if (commandWord.equals("date")) {
                // create date command: "date 2020-09-24"
                LocalDate time = LocalDate.parse(commandBody);
                return new DateCommand(time);
            } else {
                // add command
                Task newTask = Parser.parseTask(str);
                return new AddCommand(newTask);
            }
        }
    }

    public static Task parseTaskFromStorage(String taskStr) {
        Task savedTask;
        String[] split = taskStr.split(Pattern.quote(" | "));
        String description = split[2];
        boolean isDone = (Integer.parseInt(split[1]) == 1);
        if (split[0].equals("T")) {
            // create a todo
            savedTask = new Todo(description, isDone);
        } else if (split[0].equals("D")) {
            // create a deadline
            String dateStr = split[3];
            LocalDate date = LocalDate.parse(dateStr, formatter);
            savedTask = new Deadline(description, isDone, date);
        } else {
            // create an event
            String dateStr = split[3];
            LocalDate date = LocalDate.parse(dateStr, formatter);
            savedTask = new Event(description, isDone, date);
        }
        return savedTask;
    }

    public static Task parseTask(String taskStr) {
        String[] splits = taskStr.split(" ",2);
        String taskType = splits[0].toLowerCase();
        String taskBody = splits[1];
        Task newTask;

        if (taskType.equals("todo")) {
            // todo borrow book
            newTask = new Todo(taskBody.trim());
        } else if (taskType.equals("deadline")) {
            // deadline return book /by 2020-12-15
            String[] splits1 = taskBody.split("/by", 2);
            String description = splits1[0].trim();
            String timeStr = Parser.standardizeTimeString(splits1[1]);
            LocalDate time = LocalDate.parse(timeStr);
            newTask = new Deadline(description, time);
        } else {
            // event project meeting /at 2020-10-15
            String[] splits1 = taskBody.split("/at", 2);
            String description = splits1[0].trim();
            String timeStr = Parser.standardizeTimeString(splits1[1]);
            LocalDate time = LocalDate.parse(timeStr);
            newTask = new Event(description, time);
        }
        return newTask;
    }

    public static String standardizeTimeString(String timeStr) {
        return timeStr.replaceAll(" ","").replaceAll("/", "-").trim();
    }

}
