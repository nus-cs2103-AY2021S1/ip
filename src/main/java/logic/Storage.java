package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                    DateTimeFormatter dateTimeFormat2 = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy");
                    if (entry.startsWith("[T]")) {
                        ToDo t = new ToDo(entry.substring(7));
                        if (entry.contains("✓")) {
                            t.completeTask();
                        }
                        toDoList.add(t);
                    } else if (entry.startsWith("[D]")) {
                        int index = entry.indexOf("(");
                        String datetime = entry.substring(index + 5, entry.length() - 1);
                        LocalDateTime ldt = LocalDateTime.parse(datetime, dateTimeFormat2);
                        Deadline d = new Deadline(entry.substring(7, index - 1),
                                ldt);
                        if (entry.contains("✓")) {
                            d.completeTask();
                        }
                        toDoList.add(d);
                    } else if (entry.startsWith("[E]")) {
                        int index = entry.indexOf("(");
                        String datetime = entry.substring(index + 5, entry.length() - 1);
                        LocalDateTime ldt = LocalDateTime.parse(datetime, dateTimeFormat2);
                        Event e = new Event(entry.substring(7, index - 1),
                                ldt);
                        if (entry.contains("✓")) {
                            e.completeTask();
                        }
                        toDoList.add(e);
                    } else {
                        throw new InvalidSaveFileException("Hmmmm.....there seems to be an entry"
                                + "that does not follow the convention!");
                    }
                }
            }
        } catch (IOException e) {
            throw new InvalidSaveFileException("Hmmmm....there is something wrong with the previous save file! We "
                    + "will have to write a new one!");
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
                    "\t☹ OOPS!!! logic.Duke is experiencing IO errors when writing to save file!");
        }
    }
}
