package duke.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates a storage object that stores a list of tasks in a fixed path.
 */
public class Storage {

    private ArrayList<Task> al;
    private String filePath;

    /**
     * Initialises a storage object containing a list of tasks.
     * @param filePath File path to store list of tasks in.
     */
    protected Storage(String filePath) {
        al = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * Loads a previously stored list of tasks from the file.
     * If no file is found, then a file is created.
     * @return an ArrayList of tasks.
     */
    protected ArrayList<Task> load() {
        try {
            //creating path name that can be used on different OS
            String[] path = filePath.split("/", 2);
            java.nio.file.Path dataPath = java.nio.file.Paths.get(".", path[0]);
            java.nio.file.Path dukePath = java.nio.file.Paths.get(".", path[0], path[1]);

            File dataFile = new File(String.valueOf(dataPath));
            File duke = new File(String.valueOf(dukePath));

            //if both files exist
            if (dataFile.exists() && duke.exists()) {
                FileReader fr = new FileReader(duke);
                BufferedReader br = new BufferedReader(fr);
                String line;

                //reading past tasks from the file and convert them into a task list
                while ((line = br.readLine()) != null) {
                    String[] task = line.split(" // ", 4);
                    addTaskToArrayList(task, al);
                }
                fr.close();

            //dataFile exists but duke does not
            } else if (dataFile.exists()) {
                assert !duke.exists();
                duke.createNewFile();

            //both files do not exist
            } else {
                assert !dataFile.exists() && !duke.exists();
                dataFile.mkdir();
                duke.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return al;
    }

    private static void addTaskToArrayList(String[] task, ArrayList<Task> al) {
        /*string array "task" contains the following:
        [0]: type of task i.e. T, D or E
        [1]: boolean isDone represented by 0 or 1, where 0 is not done and 1 is done
        [2]: task description
        [3]: task date (if any)
        */
        boolean isDone = Integer.parseInt(task[1]) == 1;
        switch (task[0]) {
        case "T":
            Todo td = new Todo(task[2], isDone);
            al.add(td);
            break;
        case "D":
            LocalDate dlDate = LocalDate.parse(task[3]);
            Deadline dl = new Deadline(task[2], isDone, dlDate);
            al.add(dl);
            break;
        case "E":
            LocalDate eDate = LocalDate.parse(task[3]);
            Event e = new Event(task[2], isDone, eDate);
            al.add(e);
            break;
        default:
            System.out.println("task cannot be added");
        }
    }

    /**
     * Uploads a list of tasks to the file.
     * @param tasks List of tasks to be uploaded.
     * @throws YooException If the file is not found in the path.
     */
    protected void upload(TaskList tasks) throws YooException {
        //creating path name that can be used on different OS
        String[] path = filePath.split("/", 2);
        java.nio.file.Path dukePath = java.nio.file.Paths.get(path[0], path[1]);
        File duke = new File(String.valueOf(dukePath));

        try {
            FileWriter fw = new FileWriter(duke, false);

            //converting task list into text and input it into data file for storage
            for (int i = 0; i < tasks.length(); i++) {
                Task t = tasks.get(i);
                int isDone = t.getStatus() ? 1 : 0;

                switch (t.getClass().getName()) {
                case "duke.task.Todo":
                    fw.write("T // " + isDone + " // " + t.getDescription() + " // \n");
                    break;
                case "duke.task.Deadline":
                    Deadline dl = (Deadline) t;
                    fw.write("D // " + isDone + " // " + dl.getDescription() + " // " + dl.by + "\n");
                    break;
                case "duke.task.Event":
                    Event e = (Event) t;
                    fw.write("E // " + isDone + " // " + e.getDescription() + " // " + e.at + "\n");
                    break;
                default:
                    System.out.println("task not written");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new YooException("File not found!");
        }
    }
}
