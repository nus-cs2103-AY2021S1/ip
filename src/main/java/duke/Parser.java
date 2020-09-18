package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Class to store methods for parsing
 */
public class Parser {
    private static final String[] dateFormats = new String[]{"yyyy-MM-dd", "dd-MM-yyyy", "yyyy/MM/dd", "dd/MM/yyyy",
        "MMM d yyyy", "d MMM yyyy"};

    /**
     * @param input the date to be parsed
     * @return a parsed date
     * @throws IllegalArgumentException exception if the date cannot be parsed to one of the pre-specified format
     */
    public static LocalDate parseDate(String input) throws IllegalArgumentException {
        for (String it : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(it);
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ignored) {

            }
        }
        throw new IllegalArgumentException("☹ OOPS!!! I can't recognize the date provided");
    }

    private static Todo parseTodo(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return new Todo(desc);
    }

    private static Deadline parseDeadline(String desc) {
        desc = desc.trim();
        if (!desc.contains("/by")) {
            throw new IllegalArgumentException();
        }
        String[] elements = desc.split("/by");
        if (elements.length != 2) {
            throw new IllegalArgumentException();
        }
        String newDesc = elements[0].trim();
        String time = elements[1].trim();
        return new Deadline(newDesc, time);
    }

    private static Event parseEvent(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (desc.contains("/between")) {
            String[] elements = desc.split("/between");
            String[] times = elements[1].split("and");
            if (times.length != 2) {
                throw new IllegalArgumentException();
            }
            String newDesc = elements[0].trim();
            String happenAt = times[0].trim();
            String endAt = times[1].trim();
            return new Event(newDesc, happenAt, endAt);
        } else {
            String[] elements = desc.split("/at");
            if (elements.length != 2) {
                throw new IllegalArgumentException();
            }
            String newDesc = elements[0].trim();
            String time = elements[1].trim();
            return new Event(newDesc, time);
        }
    }

    /**
     * Parse the task from the raw input
     *
     * @param input the raw description about the task
     * @return a parsed task of 3 types Event, Todo or Deadline
     */
    public static Task parseTask(String input) {
        input = input.trim();
        try {
            if (input.startsWith("todo")) {
                return parseTodo(input.substring(4));
            } else if (input.startsWith("deadline")) {
                return parseDeadline(input.substring(8));
            } else if (input.startsWith("event")) {
                return parseEvent(input.substring(5));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * load the saved list of tasks
     *
     * @return the data of the file provided in the constructor
     */
    public static List<Task> parseSavedData(List<String> extData) throws IllegalStateException {
        List<Task> tasks = new ArrayList<>();
        Queue<String> data = new LinkedList<>(extData);
        while (!data.isEmpty()) {
            try {
                String type = data.poll();
                String desc = data.poll();
                int status = Integer.parseInt(Objects.requireNonNull(data.poll()));
                switch (type) {
                case "todo":
                    tasks.add(new Todo(desc));
                    break;
                case "deadline":
                    String by = data.poll();
                    tasks.add(new Deadline(desc, by));
                    break;
                case "event":
                    String at = data.poll();
                    if (!data.isEmpty()) {
                        try {
                            Parser.parseDate(data.peek());
                        } catch (IllegalArgumentException e) {
                            tasks.add(new Event(desc, at));
                            break;
                        }
                        tasks.add(new Event(desc, at, data.poll()));
                        break;
                    }
                    tasks.add(new Event(desc, at));
                    break;
                default:
                    throw new IllegalStateException();
                }
                if (status == 1) {
                    tasks.get(tasks.size() - 1).setDone();
                }
            } catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
                throw new IllegalStateException("The data file is corrupted");
            }
        }
        return tasks;
    }
}
