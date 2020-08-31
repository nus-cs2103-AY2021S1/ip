package main.java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static File tmpFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "List.txt");
    private static FileWriter writer;
    private static ArrayList<Task> taskList;
    protected static void createNewFile() {
        try {
            if (!tmpFile.exists()) {
                tmpFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static File getTmpFile() {
        return tmpFile;
    }

    protected static void writeToFile() throws DukeExceptions {
        try {
            taskList = TaskList.getThingsOnList();
            writer = new FileWriter(tmpFile);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).getFullText() + "\n");
            }
            writer.close();
            System.out.println("List Saved!");
        } catch (IOException e) {
            throw new DukeExceptions("Sorry, I couldn't save that list before closing.");
        }
    }
}
