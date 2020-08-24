package commands;

import duke.Storage;
import duke.Ui;
import exceptions.InvalidTaskException;
import exceptions.UnknownCmdException;
import exceptions.InvalidTimeException;
import exceptions.InvalidFileException;
import exceptions.BadDtFormatException;
import tasks.TaskList;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    String text;

    public AddCommand(String text) {
        this.text = text;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskException, UnknownCmdException,
            InvalidTimeException, InvalidFileException, BadDtFormatException {
        String[] info = extractInfo(text);
        if (info[0].equals("todo")) {
            tasks.addItem(new Todo(info[1], false));
            ui.printAddTask(info[1]);
            ui.printListSize(tasks.size());
            storage.writeToFile("data.txt", tasks.writeString());
        } else if (info[0].equals("deadline")) {
            tasks.addItem(new Deadline(info[1], stringToTime(info[2]), false));
            ui.printAddTask(info[1]);
            ui.printListSize(tasks.size());
            storage.writeToFile("data.txt", tasks.writeString());
        } else {
            tasks.addItem(new Event(info[1], stringToTime(info[2]), false));
            ui.printAddTask(info[1]);
            ui.printListSize(tasks.size());
            storage.writeToFile("data.txt", tasks.writeString());
        }
    }

    /**
     * Breaks down and stores user input in a string array for easy access
     * @param str String to be broken down
     * @return A string array split according to the information categories
     * @throws InvalidTaskException If task description is empty
     * @throws UnknownCmdException If an unknown command is entered
     * @throws InvalidTimeException If an invalid time is entered for a Deadline or Event task
     */

    public String[] extractInfo(String str) throws InvalidTaskException, UnknownCmdException,InvalidTimeException {
        String[] store = new String[3];
        // Handling the classification of event type
        if (str.startsWith("todo")) {
            if (str.equals("todo") || str.strip().equals("todo"))  {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("todo ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "todo";
            }
        } else if (str.startsWith("deadline")) {
            if (str.equals("deadline") || str.strip().equals("deadline")) {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("deadline ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "deadline";
            }
        } else if (str.startsWith("event")) {
            if (str.equals("event") || str.strip().equals("event")) {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("event ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "event";
            }
        } else {
            throw new UnknownCmdException("Unknown command entered!");
        }

        // handling the content of the event
        int splitPrefix = str.indexOf(" ");
        String content = str.substring(splitPrefix).strip();
        if (content.length() <= 0) {
            throw new InvalidTaskException("Your task cannot be empty!");
        }
        if (store[0].equals("todo")) {
            store[1] = content;
            store[2] = "";
        } else {
            int splitTime = store[0].equals("deadline") ? content.indexOf("/by") : content.indexOf("/at");
            if (splitTime < 0) {
                throw new InvalidTimeException("Please use /by (deadlines) or /at (events)! to indicate the date or time!");
            }
            String name = content.substring(0, splitTime).strip();
            String time = content.substring(splitTime + 3).strip();
            if (name.length() <= 0) {
                throw new InvalidTaskException("Your task cannot be empty!");
            }
            if (time.length() <= 0) {
                throw new InvalidTimeException("Please specify a date or time for this task!");
            }
            store[1] = name;
            store[2] = time;
        }
        return store;
    }

    /**
     * Converts a string into a LocalDateTime
     * @param time The string to be parsed
     * @return A LocalDateTime object
     * @throws BadDtFormatException If an invalid string format is entered
     */
    public LocalDateTime stringToTime(String time) throws BadDtFormatException {
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy H:m"));
        } catch (DateTimeParseException e) {
            throw new BadDtFormatException("Please enter the date and time in the following format: dd/mm/yyyy hh:mm "
                    + "(24 hour clock)");
        }
    }
}
