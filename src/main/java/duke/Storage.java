package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.CalendarException;
import duke.exception.DukeException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {

    private static String filePath;
    //private TaskList taskList;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Reads the file from hard disk.
     *
     * @return List containing the parsed data from file.
     * @throws IOException   If file cannot be read or found.
     * @throws DukeException If there is incorrect format in the file.
     */
    public static List<Task> readFile() throws IOException, DukeException {
        Path folder = Path.of("data");
        Path file = folder.resolve(filePath);

        //Create a new directory by creating all parent directories first
        Files.createDirectories(folder);

        //if user is new and file does not exist, create the file
        if (!Files.exists(file)) {
            Files.createFile(file);
        }

        //read from file
        BufferedReader reader = Files.newBufferedReader(file);
        List<Task> tasks = new ArrayList<>(); //this does not update this.todos

        reader.lines().forEach(line -> {
            try {
                Task task = parseData(line);
                tasks.add(task);
            } catch (StorageException e) {
                System.out.println(e.getMessage());
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            }
        });
        return tasks;
    }

    /**
     * Formats the data to more readable format.
     *
     * @param line String to be parsed.
     * @return parsed Task.
     * @throws DukeException If there is incorrect data format.
     */
    private static Task parseData(String line) throws DukeException {

        try {
            String[] parsed = line.split("\\s\\|\\s");

            if (parsed.length < 2) {
                throw new StorageException(line + "is in invalid format.");
            } else {
                String identifier = parsed[0]; //get the type of duke.task

                if (identifier.equals("T")) {
                    return parseTodoData(parsed);

                } else if (identifier.equals("E")) {
                    return parseEventData(parsed);

                } else if (identifier.equals("D")) {
                    return parseDeadlineData(parsed);

                } else {
                    throw new StorageException("Invalid format. Moving on to the next task.");
                }

            }

        } catch (DateTimeParseException e) {
            throw new CalendarException("Please input the correct date and time format. "
                + "YYYY-MM-DD for date and HH:MM for time.");
        }
    }

    /**
     * Format the event data to more readable format.
     *
     * @param parsed split Input.
     * @return parsed Event data.
     */
    public static Event parseEventData(String[] parsed) {
        String doneIndicator = parsed[1];
        String taskName = parsed[2];
        String date = parsed[3];
        LocalDate localDate = LocalDate.parse(date);
        Event event;
        if (parsed.length == 4) {
            if (doneIndicator.equals("1")) {
                event = new Event(taskName, true, localDate);
            } else {
                event = new Event(taskName, localDate);
            }
        } else {
            String time = parsed[4];
            String[] startEndTime = time.split("-");
            String startTime = startEndTime[0];
            LocalTime localStartTime = LocalTime.parse(startTime);
            if (startEndTime.length < 2) {
                if (doneIndicator.equals("1")) {
                    event = new Event(taskName, true, localDate, localStartTime);
                } else {
                    event = new Event(taskName, false, localDate, localStartTime);
                }
            } else {
                String endTime = startEndTime[1];
                LocalTime localEndTime = LocalTime.parse(endTime);
                if (doneIndicator.equals("1")) {
                    event = new Event(taskName,
                        true, localDate, localStartTime, localEndTime);
                } else {
                    event = new Event(taskName,
                        false, localDate, localStartTime, localEndTime);
                }
            }
        }
        return event;
    }

    /**
     * Format the deadline data to more readable format.
     *
     * @param parsed split Input.
     * @return parsed Deadline data.
     */
    public static Deadline parseDeadlineData(String[] parsed) {
        String doneIndicator = parsed[1];
        String taskName = parsed[2];
        String date = parsed[3];
        LocalDate localDate = LocalDate.parse(date);
        Deadline deadline;

        if (parsed.length < 5) {
            if (doneIndicator.equals("1")) {
                deadline = new Deadline(taskName, true, localDate);
            } else {
                deadline = new Deadline(taskName, localDate);
            }
        } else {
            String time = parsed[4];
            LocalTime localTime = LocalTime.parse(time);
            if (doneIndicator.equals("1")) {
                deadline = new Deadline(taskName, true, localDate, localTime);
            } else {
                deadline = new Deadline(taskName, false, localDate, localTime);
            }
        }
        return deadline;
    }

    /**
     * Format the todo data to more readable format.
     *
     * @param parsed split Input.
     * @return parsed Todo data.
     */
    public static Todo parseTodoData(String[] parsed) {
        String doneIndicator = parsed[1];
        String taskName = parsed[2];
        Todo todo;

        if (doneIndicator.equals("1")) {
            todo = new Todo(taskName, true);
        } else {
            todo = new Todo(taskName);
        }

        return todo;
    }

    /**
     * Updates the data in the file.
     *
     * @param tasks List containing all the updated tasks.
     */
    public static void updateData(List<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of("data/tasks.txt"));
            for (Task task : tasks) {
                String type = task.getType();
                Boolean status = task.getStatus();
                String taskName = task.getDescription();
                String stored = "";

                if (type.equals("T")) {
                    stored = String.format("%s | %d | %s", type, status ? 1 : 0, taskName);

                } else if (type.equals("E")) {

                    stored = updateEventData(task);

                } else if (type.equals("D")) {

                    stored = updateDeadlineData(task);
                }
                writer.write(stored);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the Event data in the file.
     *
     * @param task Event task.
     */
    public static String updateEventData(Task task) {
        String type = task.getType();
        Boolean status = task.getStatus();
        String taskName = task.getDescription();
        String stored = "";
        Event event = (Event) task;

        LocalDate date = event.getEventDate();
        LocalTime startTime = event.getStartTime();
        LocalTime endTime = event.getEndTime();

        if (startTime == null && endTime == null) {
            stored = String.format(
                "%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
        } else if (endTime == null) {
            stored = String.format("%s | %d | %s | %s | %s",
                type, status ? 1 : 0, taskName, date, startTime);
        } else {
            stored = String.format("%s | %d | %s | %s | %s-%s",
                type, status ? 1 : 0, taskName, date, startTime, endTime);
        }

        return stored;
    }

    /**
     * Updates the Deadline data in the file.
     *
     * @param task Deadline task.
     */
    public static String updateDeadlineData(Task task) {
        String type = task.getType();
        Boolean status = task.getStatus();
        String taskName = task.getDescription();
        String stored = "";
        Deadline deadline = (Deadline) task;

        LocalDate date = deadline.getDeadline();
        LocalTime time = deadline.getDeadlineTime();

        if (time == null) {
            stored = String.format("%s | %d | %s | %s",
                type, status ? 1 : 0, taskName, date);
        } else {
            stored = String.format("%s | %d | %s | %s | %s",
                type, status ? 1 : 0, taskName, date, time);

        }

        return stored;
    }
}
