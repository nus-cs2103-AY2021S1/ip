import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

    private DukeList list;

    Command(DukeList list) {
        this.list = list;
    }

    public String processCommand(String command) throws DukeException {
        Pattern pattern = Pattern.compile("^(.*?)\\s(.*?)(?:\\s/..\\s(.*))?$");
//        String[] stringArray = command.split(" ");
//        List<String> stringList = new ArrayList<>(Arrays.asList(stringArray));
//        String com = stringList.remove(0);
        Matcher matcher = pattern.matcher(command);
        if (command.equals("list")) {
            return this.list.toString();
        }
        if (command.equals("hello")) {
            return "Hi! I'm Duke! Pleasure to meet you :)";
        }
        if (matcher.find()) {
            String com = matcher.group(1);
            String task = matcher.group(2);
            String date = matcher.group(3);
            String index = matcher.group(2);

            switch (com) {
                case ("undo"):
                case ("done"):
                case ("delete"):
                    try {
                        return this.processList(com, index);
                    } catch (Exception e) {
                        return e.getMessage();
                    }

                case ("todo"):
                case ("deadline"):
                case ("event"):
                    try {
                        return this.processTask(com, task, date);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
            }
        }
        throw new DukeException("Sorry, I did not understand: " + command);
    }

    private String processTask(String com, String task, String date) throws DukeException {

        switch(com) {
            case("todo"):
                if (!task.equals("")) {
                    return list.addItem(new Todo(task));
                } else {
                    throw new DukeException("Please write a task to be done, with \"todo <task>\"");
                }
            case("deadline"):
                if (!task.equals("")) {
                    try {
                        return list.addItem(new Deadline(task, Command.convertDate(date)));
                    } catch (DateTimeParseException e){
                        return "Please write your date in the format \"dd/MM/yyyy\"";
                    }
                } else {
                    throw new DukeException("Please write a deadline, with \"deadline <task> /by <date>\"");
                }
            case("event"):
                if (!task.equals("")) {
                    try {
                        return list.addItem(new Event(task, Command.convertDate(date)));
                    } catch (DateTimeParseException e) {
                        return "Please write your date in the format \"dd/MM/yyyy\"";
                    }
                } else {
                    throw new DukeException("Please write an event, with \"event <task> /at <date>\"");
                }
            default:
                return null;
        }
    }

    private String processList(String com, String index) throws DukeException {

        switch(com) {
            case("done"):
                if (!index.equals("")) {
                    return list.markDone(Integer.parseInt(index) - 1);
                } else {
                    throw new DukeException("Please choose a task to mark as done, with \"done <task number>\"");
                }
            case("undo"):
                if (!index.equals("")) {
                    return list.revertDone(Integer.parseInt(index) - 1);
                } else {
                    throw new DukeException("Please choose a task to undo, with \"undo <task number>\"");
                }
            case("delete"):
                if (!index.equals("")) {
                    return list.deleteItem(Integer.parseInt(index) - 1);
                } else {
                    throw new DukeException("Please choose a task to delete, with \"delete <task number>\"");
                }
            default:
                return null;
        }
    }

    private static LocalDate convertDate(String date) throws DateTimeParseException {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
