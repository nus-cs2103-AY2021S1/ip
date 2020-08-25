package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String command = input.split(" ")[0];
        try {
            switch (command) {
            case "bye":
                return new Command("bye");
            case "list":
                return new Command("list");
            case "done":
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                return new Command("done" , idx);
            case "delete":
                int idx2 = Integer.parseInt(input.split(" ")[1]) - 1;
                return new Command("delete" , idx2);
            case "todo":
                taskFormatCheck(command, input);
                String desc = input.substring(input.indexOf(' ') + 1);
                return new Command("todo", desc);
            case "deadline":
            case "event":
                taskFormatCheck(command, input);
                String info2 = input.substring(input.indexOf(' ') + 1);
                String desc2 = info2.substring(0, info2.indexOf('/') - 1);
                String meta = info2.substring(info2.indexOf('/') + 4);
                LocalDate date;
                if (meta.contains(" ")) {
                    date = LocalDate.parse(meta.substring(0, meta.indexOf(' ')),
                            DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    LocalTime time = LocalTime.parse(meta.substring(meta.indexOf(' ') + 1),
                            DateTimeFormatter.ofPattern("HHmm"));
                    return new Command(command, desc2, date, time);
                } else {
                    date = LocalDate.parse(meta, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    return new Command(command, desc2, date);
                }
            default:
                throw new DukeException("Oh dear! I'm sorry, but I don't know what that means :(");
            }
        } catch (DateTimeParseException ex) {
            throw new DukeException("Oh dear! Please format the date and time as yyyy/MM/dd HHmm.");
        }
    }

    private static void taskFormatCheck(String type, String input) throws DukeException {
        int idxSpace = input.indexOf(' ');
        int idxMeta = input.indexOf('/');
        int infoLength = idxMeta - (idxSpace + 1);
        if (idxSpace == -1 ||
                (idxMeta != -1 && infoLength < 1)) {
            throw new DukeException("Oh dear! A task description cannot be empty!");
        }
        String info = input.substring(input.indexOf(' ') + 1);
        if (type.equals("todo") && info.contains("/")) {
            throw new DukeException("Oh dear! A todo shouldn't contain a timestamp!");
        }
        if (type.equals("deadline") && !info.contains("/by")) {
            throw new DukeException("Oh dear! A deadline must contain '/by'!");
        }
        if (type.equals("event") && !info.contains("/at")) {
            throw new DukeException("Oh dear! An event must contain '/at'!");
        }
    }
}