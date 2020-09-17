package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles saving and leading tasks to and from
 * files saved in the hard disk. A storage object is able to read
 * file paths independent of OS.
 * If directory or file is not found, a Storage object will handle
 * it by creating a new directory or file when required.
 */
public class Storage {
    /**
     * Returns tasks stored as a String.
     *
     * @param taskList given list of tasks.
     * @return String list of tasks in String format.
     */
    public String listToString(ArrayList<Task> taskList) {
        String taskListStr = "";
        for (Task t : taskList) {
            taskListStr += t.toStringFileFormat() + "\n";
        }
        return taskListStr;
    }

    /**
     * Saves list of tasks as a text file on the hard drive.
     *
     * @param userTasks list of tasks to be saved.
     */
    public void saveToFile(ArrayList<Task> userTasks) {
//        Path folderPath = Paths.get("..", "..", "..", "data");
        Path folderPath = Paths.get("data");
        if (!Files.exists(folderPath)) {
            File folderDir = new File(folderPath.toString());
            folderDir.mkdir();
        }

//        String filePath = Paths.get("..", "..", "..", "data", "Tasklist.txt")
//                .toString();
        String filePath = Paths.get("data", "Tasklist.txt")
                .toString();

        try {
            FileWriter myFile = new FileWriter(filePath);
            myFile.write(listToString(userTasks)); // Output is already all tasks in a string
            myFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads tasks from text file in hard drive
     * and returns them as a list.
     *
     * @return ArrayList<Task> list of tasks read from file.
     */
    public ArrayList<Task> readFromFile() {

//        Path folderPath = Paths.get("..", "..", "..", "data");
        Path folderPath = Paths.get("data");
        if (!Files.exists(folderPath)) {
            File folderDir = new File(folderPath.toString());
            folderDir.mkdir();
        }

        ArrayList<Task> savedTasks = new ArrayList<Task>();

//        Path filePath = Paths.get("..", "..", "..", "data", "Tasklist.txt");
        Path filePath = Paths.get("data", "Tasklist.txt");
        if (!Files.exists(filePath)) {
            return savedTasks;
        }

        try {
            File myFile = new File(filePath.toString());
            Scanner taskReader = new Scanner(myFile);

            while (taskReader.hasNextLine()) {
                String taskString = taskReader.nextLine();

                if (taskString.equals("")) {
                    continue;
                }

                switch (taskString.charAt(1)) {
                case 'T':
                    boolean isDone = taskString.split("  ")[0]
                            .substring(3)
                            .equals("[Done]");
                    String description = " " + taskString.split("  ")[1];
                    Task t = new ToDo(description);
                    if (isDone) {
                        t.setDone();
                    }
                    savedTasks.add(t);
                    break;
                case 'D':
                    isDone = taskString.split("  ")[0]
                            .substring(3)
                            .equals("[Done]");
                    description = " " + taskString.split("  ")[1]
                            .split("\\s[(]by:\\s")[0];
                    String by = taskString.split("  ")[1]
                            .split("\\s[(]by:\\s")[1];
                    by = by.substring(0, by.length() - 1); // remove parentheses at the end
                    Deadline d = new Deadline(description, by);
                    if (isDone) {
                        d.setDone();
                    }
                    savedTasks.add(d);
                    break;
                case 'E':
                    isDone = taskString.split("  ")[0]
                            .substring(3)
                            .equals("[Done]");
                    String[] stringSplit = taskString.split("  ")[1]
                            .split("\\s[(]at:\\s");
                    description = " " + stringSplit[0];
                    String at = stringSplit[1].split(" ")[0];
                    String timeRange = stringSplit[1].split(" ")[1];
                    timeRange = timeRange.substring(0, timeRange.length() - 1); // remove parentheses at the end
                    Event e = new Event(description, at, timeRange);
                    if (isDone) {
                        e.setDone();
                    }
                    savedTasks.add(e);
                    break;
                default:
                    throw new IOException();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return savedTasks;
    }
}
