package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.InvalidSaveFileException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Handles saving of the list and reading it upon startup.
 */
public class Storage {
    private String fileLocation;
    public Storage(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * Called upon startup. Reads save file if there is one, else creates
     * a blank save file.
     *
     * @return A List populated by the tasks.Task from previous save file.
     * @throws InvalidSaveFileException If there is an issue with creating the save file.
     */
    public List<Task> readFile() throws InvalidSaveFileException {
        List<Task> toDoList = new ArrayList<Task>();
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = new File(fileLocation);
            if (file.exists()) {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String entry = s.nextLine();
                    if (entry.startsWith("[T]")) {
                        toDoList.add(createToDo(entry));
                    } else if (entry.startsWith("[D]")) {
                        toDoList.add(createDeadline(entry));
                    } else if (entry.startsWith("[E]")) {
                        toDoList.add(createEvent(entry));
                    } else {
                        throw new InvalidSaveFileException("Hmmmm.....there seems to be an entry"
                                + "that does not follow the convention!");
                    }
                }
            }
        } catch (IOException e) {
            throw new InvalidSaveFileException("Hmmmm....there is something wrong with the previous save file! We "
                    + "will have to write a new one!");
        } catch (DateTimeParseException err) {
            throw new InvalidSaveFileException("Hmmmm.....seems like an entry was saved wrongly!"
                    + " Lets write a new save!");
        }
        return toDoList;
    }

    /**
     * Called every time the list is updated or logic.Duke is closed.
     *
     * @param toDoList The current tasks in logic.Duke.
     * @throws InvalidSaveFileException If there is an issue creating or writing the save file.
     */
    public void saveFile(List<Task> toDoList) throws InvalidSaveFileException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(fileLocation);
            for (int i = 0; i < toDoList.size(); i++) {
                Task interim = toDoList.get(i);
                String desc = interim.toString();
                fw.write(desc + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidSaveFileException(
                    "☹ OOPS!!! logic.Duke is experiencing IO errors when writing to save file!");
        }
    }

    private ToDo createToDo(String entry) {
        final int STARTING_INDEX = 7;
        ToDo todo = new ToDo(entry.substring(STARTING_INDEX));
        if (entry.contains("✓")) {
            todo.completeTask();
        }
        return todo;
    }

    private Deadline createDeadline(String entry) {

        //format of example saved entry: [D][✓] return book (by: 13:56 Aug 23 2020)
        final int DATETIME_INDEX = entry.indexOf("(") + 5;
        final int DESC_INDEX = 7;

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy");

        String datetime = entry.substring(DATETIME_INDEX, entry.length() - 1);
        LocalDateTime ldt = LocalDateTime.parse(datetime, dateTimeFormat);
        Deadline deadline = new Deadline(entry.substring(DESC_INDEX, DATETIME_INDEX - 1),
                ldt);
        if (entry.contains("✓")) {
            deadline.completeTask();
        }
        return deadline;
    }

    private Event createEvent(String entry) {

        //format of example saved entry: [E][✓] return book (at: 13:56 Aug 23 2020)
        final int DATETIME_INDEX = entry.indexOf("(") + 5;
        final int DESC_INDEX = 7;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy");

        String datetime = entry.substring(DATETIME_INDEX, entry.length() - 1);
        LocalDateTime ldt = LocalDateTime.parse(datetime, dateTimeFormat);
        Event event = new Event(entry.substring(DESC_INDEX, DATETIME_INDEX - 1),
                ldt);
        if (entry.contains("✓")) {
            event.completeTask();
        }
        return event;
    }
}
