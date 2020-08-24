import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    TaskList taskList;
    Storage storage;
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }
    public String scenarios(String input) throws DukeException {
        String[] instructions = input.split(" ");
        String command = instructions[0];
        switch (command) {
            case "bye":
                storage.overwriteTodoList();
                return command;
            case "list":
                return taskList.showList();
            case "done":
                return taskList.completeItem(input);
            case "delete":
                return taskList.deleteItem(input);
            case "todo":
                String todo = input.substring(4, input.length());
                return taskList.addItem(command, todo);
            case "deadline":
                String deadline = input.substring(8, input.length());
                return taskList.addItem(command, deadline);
            case "event": {
                String event = input.substring(5, input.length());
                return taskList.addItem(command, event);
            }
            default:
                throw new DukeException("Oops! I'm sorry but I have no idea what that means >.<");
        }
    }

    public static LocalDate formatDate(String dateTime) throws DukeException {
        try {
            String[] split = dateTime.split(" ");
            String[] dayMonthYear = split[0].split("/");
            String date = dayMonthYear[2] + "-" + dayMonthYear[1] + "-";
            if (dayMonthYear[0].length() == 1) {
                date += "0" + dayMonthYear[0];
            } else {
                date += dayMonthYear[0];
            }
            LocalDate dueDate = LocalDate.parse(date);
            return dueDate;
        } catch (Exception e) {
            throw new DukeException("Incorrect format of date. It should be: DD/MM/YYYY 2359");
        }
    }

    public static String formatDateString(LocalDate dateTime, String dueDate) throws DukeException {
        try {
            String time = "2359";
            String[] split = dueDate.split(" ");
            if (split.length == 2) {
                time = split[1];
            }
            String format = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return format + " " + formatTime(time);
        } catch (Exception e) {
            throw new DukeException("Incorrect format of date. It should be: DD/MM/YY time");
        }
    }

    public static String formatTime(String time) {
        int firstHour = (int) time.charAt(0) - '0';
        int secondHour = (int) time.charAt(1) - '0';
        int hours = firstHour * 10 + secondHour;
        String amPM;
        String convertedTime;
        if (hours < 12) {
            amPM = "am";
        } else {
            amPM = "pm";
        }
        hours = hours % 12;
        if (hours == 0) {
            convertedTime = "12";
        } else {
            convertedTime = Integer.toString(hours);
        }
        return convertedTime + "." + time.substring(2, 4) + amPM;
    }

    public static Deadline parseDeadline(String item, boolean completed) throws DukeException {
        String[] splitItem = item.split("/by ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a deadline to finish task by.");
        }
        LocalDate dueDate = Parser.formatDate(splitItem[1]);
        String dateTime = Parser.formatDateString(dueDate, splitItem[1]);
        return new Deadline(splitItem[0], dateTime, dueDate, splitItem[1], completed);
    }

    public static Event parseEvent(String item, boolean completed) throws DukeException {
        String[] splitItem = item.split("/at ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a time/date the event is held at.");
        }
        LocalDate dueDate = Parser.formatDate(splitItem[1]);
        String dateTime = Parser.formatDateString(dueDate, splitItem[1]);
        return new Event(splitItem[0], dateTime, dueDate, splitItem[1], completed);
    }
}
