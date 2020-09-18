package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Store and Retrieve tasks from the task list
 */
public class Storage {
    private static String  folderPath = "Data";
    private static String writePath = "Data/Duke.txt";

    /**
     * Writes tasks to file
     * @param taskList List of tasks to be written to the file
     * @throws IOException
     */
    public static void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(writePath);
        fw.write("");
        for (Task task : taskList.getListOfTasks()) {
            fw.append(task.storeFormat() + "\n");
        }
        fw.close();
    }

    /**
     * Loads tasks from Database to be stored in the taskList
     * @return list of Tasks
     */
    public static List<Task> loadFile() throws IOException{
        List<Task> tasks = new ArrayList<>();
            File f = new File(writePath);
            try {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskString = s.nextLine();
                    tasks.add(generateTask(taskString));
                }
                return tasks;
            } catch (FileNotFoundException e) {
                System.out.println("Initializing storage");
                File f2 = new File(folderPath);
                f2.mkdir();
                return tasks;
            }
    }

    /**
     * Reads tasks from the database
     * @param taskString Storage string that represents the task
     * @return the actual task object
     */
    public static Task generateTask(String taskString) {
        Task task = null;
        String [] arr = taskString.split("\\s+");
        String tag;
        boolean isCompleted = booleanToString(arr[1]);
        if (arr[0].equals("[D]")) {
            task = generateDeadline(arr,isCompleted);
            
        } else if (arr[0].equals("[E]")) {
            tag = arr[4];
            task = new Event(arr[2],isCompleted,arr[3]);
            task.reTag(tag);
        } else if (arr[0].equals("[T]")) {
            tag=arr[3];
            task = new Todo(arr[2], isCompleted);
            task.reTag(tag);
        } else {
            System.out.println("This task cannot be read: " + taskString);
        }
        return task;
    }
    
    private static Deadline generateDeadline(String[] arr, boolean isCompleted) {
        String deadline = arr[arr.length-3] + " " +arr[arr.length-2];
        String tag = arr[arr.length-1];
        String description = arr[2];
        int i;
        for (i =3 ; i < arr.length-2 ;i++) {
            description += " " + arr[i];
        }
        Deadline dLine = new Deadline(description,isCompleted,deadline);
        dLine.reTag(tag);
        return dLine;
    }
    
    private static boolean booleanToString (String bool) {
        if (bool.equals("false")) {
            return false;
        } else if (bool.equals("true")) {
            return true;
        }
        System.out.println("Boolean could not be deciphered");
        return false;
    }
}
