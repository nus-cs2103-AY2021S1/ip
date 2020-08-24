import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class TaskManager {
    private static String dirName = "data";
    private static String filePath = dirName + "/duke.txt";
    
    public static ArrayList<Task> readFile() {
        ArrayList<Task> result = new ArrayList<>();
        try {
            // Create the directory if it does not exist
            File dir = new File(dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                String[] taskDetails = nextLine.split(" \\| ");
                Task task;
                
                if (taskDetails[0].equals("T")) {
                    task = new ToDo(taskDetails[2]);
                } else if (taskDetails[0].equals("D")) {
                    task = new Deadline(taskDetails[2], taskDetails[3]);
                } else {
                    task = new Event(taskDetails[2], taskDetails[3]);
                }
                
                if (taskDetails[1].equals("1")) {
                    task.completeTask();
                } 
                result.add(task);
            }
        } catch (FileNotFoundException e) {
            // Create the .txt file if it does not exist
            try {
                File f = new File(filePath);
                f.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    public static void saveFile(ArrayList<Task> tasks) {
        StringJoiner text = new StringJoiner("\n");
        for (Task task : tasks) {
            text.add(task.toSaveFormat());
        }
        
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
