import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public static String  folderPath = "Data";
    public static String writePath = "Data/Duke.txt";
    
    public static void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(writePath);
        fw.write("");
        for (Task task : taskList.getListOfTasks()) {
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
            System.out.println("Initializing storage");
            File f2 = new  File(folderPath);
            f2.mkdir();
            return tasks;
        }
    }
    
    public static Task generateTask(String taskString) {
        Task task = null;
        String [] arr = taskString.split("\\s+");
        boolean isCompleted = booleanToString(arr[1]);
        if (arr[0].equals("[D]")) {
            task = generateDeadline(arr,isCompleted);
        } else if (arr[0].equals("[E]")) {
            task = new Event(arr[2],isCompleted,arr[3]);
        } else if (arr[0].equals("[T]")) {
            task = new Todo(arr[2], isCompleted);
        } else {
            System.out.println("This task cannot be read: " + taskString);
        }
        return task;
    }
    
    public static Deadline generateDeadline(String[] arr, boolean isCompleted) {
        String deadline = arr[arr.length-2] + " " +arr[arr.length-1];
        String description = arr[2];
        int i;
        for (i =3 ; i < arr.length-2 ;i++) {
            description += " " + arr[i];
        }
        return new Deadline(description,isCompleted,deadline);
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
