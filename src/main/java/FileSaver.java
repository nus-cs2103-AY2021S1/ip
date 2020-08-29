import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    public static String  filePath = "Data";
    public static String writePath = "Data/Duke.txt";
    
    public static void writeToFile(List<Task> list) throws IOException {
        File f = new File(writePath);
        FileWriter fw = new FileWriter(writePath);
        fw.write("");
        for (Task task : list) {
            fw.append(task.storeFormat() + "\n");
        }
        fw.close();
    }
    
    public static List<Task> loadFile() throws IOException {
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
            System.out.println("Creating new folder");
            File f2 = new  File(filePath);
            f2.mkdir();
            return tasks;
        }
    }
    
    public static Task generateTask(String taskString) {
        Task task = null;
        String [] arr = taskString.split("\\s+");
        boolean isCompleted = booleanToString(arr[1]);
        if (arr[0].equals("[D]")) {
            task = new Deadline(arr[2],isCompleted,arr[3]);
        } else if (arr[0].equals("[E]")) {
            task = new Event(arr[2],isCompleted,arr[3]);
        } else if (arr[0].equals("[T]")) {
            task = new Todo(arr[2], isCompleted);
        } else {
            System.out.println("This task cannot be read: " + taskString);
        }
        return task;
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
