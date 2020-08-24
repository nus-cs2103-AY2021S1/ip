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

public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public static void writeToFile(ArrayList<Task> toDoList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < toDoList.size(); i++) {
            fw.write(toDoList.get(i).infoString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(task.infoString());
        fw.write(System.lineSeparator());
        fw.close();
    }

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
