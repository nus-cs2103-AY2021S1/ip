import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class.
 * Used to read and write files to the hard drive whenever the task list is edited.
 * Will read and write to ./data/duke.txt.
 *
 * @author YanCheng
 */
public class Storage {

    public static final String FILENAME = "./data/duke.txt";
    public static final String DIRECTORY_NAME = "./data/";

    public TaskList list;

    /**
     * Constructor for Storage.
     * @param list A TaskList object
     */
    public Storage(TaskList list) {
        this.list = list;
    }

    //    // appends task info to existing duke.txt file
    //    // only can be used by add method
    //    public static void save(Task task) {
    //        try {
    //            FileWriter fw = new FileWriter(FILENAME, true);
    //            // true to mark fw to append line to existing file
    //            fw.write(task.getInfo());
    //            fw.close();
    //        } catch (IOException e) {
    //            System.out.println("File is missing");
    //        }
    //    }

    /**
     * Rewrites duke.txt file to the hard drive.
     */
    // rewrites duke.txt file by iterating though task list
    // can be used by add, delete and done method
    public void save() {
        try {
            FileWriter fw = new FileWriter(FILENAME);
            if (list.size() > 0) {
                fw.write(list.get(0).getInfo());

                for (int i = 1; i < list.size(); i++) {
                    fw.write(list.get(i).getInfo());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File is missing");
        }
    }

    /**
     * Initialises the TaskList if there is an existing duke.txt.
     */
    public void init() {
        File directory = new File(DIRECTORY_NAME);

        // checks if directory exists, else directory will be created
        if (!directory.exists()) {
            directory.mkdir();
        }

        File f = new File(FILENAME);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    String[] split = line.split("\\|");
                    if (split.length < 3) {
                        // if after split, arr contains insufficient info, skip;
                        continue;
                    }
                    String taskType = split[0];
                    String status = split[1].strip();
                    String details = split[2].stripLeading();

                    if (taskType.contains("T")) {
                        ToDo task = new ToDo(details);
                        if (status.equals("1")) {
                            task.completed();
                        }
                        list.initAdd(task);
                    } else if (taskType.contains("D")) {
                        String date = split[3].stripLeading();
                        Deadline task = new Deadline(details, date);
                        if (status.equals("1")) {
                            task.completed();
                        }
                        list.initAdd(task);
                    } else if (taskType.contains("E")) {
                        String date = split[3].stripLeading();
                        Event task = new Event(details, date);
                        if (status.equals("1")) {
                            task.completed();
                        }
                        list.initAdd(task);
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            System.out.println("File not found, will be created");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Missing input");
            }
        }
    }
}
