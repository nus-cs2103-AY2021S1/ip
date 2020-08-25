package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores user data.
 */
public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
        try {
            File f = new File(filePath);
            f.getParentFile().mkdir();
            f.createNewFile();
        } catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
    /**
     * Writes task list to file
     * @param toDoList list of tasks that user has inputted that needs to be saved
     */
    public static void writeToFile(ArrayList<Task> toDoList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < toDoList.size(); i++) {
            fw.write(toDoList.get(i).infoString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Adds an additional task to file
     * @param task the task that needs to be added to the file to be saved
     */
    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(task.infoString());
        fw.write(System.lineSeparator());
        fw.close();
    }

    /**
     * Reads file and generates the task list that was saved
     * @return the tasklist that was saved in the file
     */
    public static ArrayList<Task> readFile() throws FileNotFoundException {
        File f = new File(filePath);

        if (!f.exists()) {
            return new ArrayList<>();
        }

        Scanner s = new Scanner(f);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            listOfTasks.add(createTask(s.nextLine()));
        }

        return listOfTasks;
    }

    /**
     * Converts infoString format into a Task
     * @param s infoString of the task
     * @return task represented by the infoString
     */
    public static Task createTask(String s) {
        String[] parts = s.split("\\|");
        switch(parts[0].trim()) {
            case "T":
                return new ToDo(parts[2].trim(), parts[1].trim().equals("1"));
            case "D":
                return new Deadline(parts[2].trim(), parts[1].trim().equals("1"), parts[3].trim());
            case "E":
                return new Event(parts[2].trim(), parts[1].trim().equals("1"), parts[3].trim());

            default:
                return null;
        }
    }
}
