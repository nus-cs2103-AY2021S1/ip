package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * handle loading files and saving program's data to disk
 */
public class Storage {
    private File file;

    /**
     * Storage constructor, use to pass in the data filename
     * If the file doesn't exist, create it
     *
     * @param filename the name of the data file
     */
    public Storage(String filename) {
        try {
            file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred when interacting with file.");
            e.printStackTrace();
        }
    }

    /**
     * load the saved list of tasks
     *
     * @return the data of the file provided in the constructor
     */
    public List<Task> load() {
        List<Task> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String type = sc.nextLine();
                String desc = sc.nextLine();
                int status = Integer.parseInt(sc.nextLine());
                switch (type) {
                case "todo":
                    lst.add(new Todo(desc));
                    break;
                case "deadline":
                    String by = sc.nextLine();
                    lst.add(new Deadline(desc, by));
                    break;
                case "event":
                    String at = sc.nextLine();
                    lst.add(new Event(desc, at));
                    break;
                default:
                    throw new IllegalStateException("The data file is corrupted");
                }
                if (status == 1) {
                    lst.get(lst.size() - 1).setDone();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred when interacting with file.");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
        return lst;
    }

    /**
     * save the current list of tasks to disk
     *
     * @param lst list of all tasks
     */
    public void write(List<Task> lst) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : lst) {
                writer.write(task.toDisk() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when write to file");
            e.printStackTrace();
        }

    }
}
