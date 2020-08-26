package Duke.Tool;

import Duke.Task.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Load and write data to data.txt.
 */
public class Storage {
    
    private String filePath;

    /**
     * Construct a storage class.
     * @param filePath path of the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Find the index of nth occurrence of a string.
     * @param str input string
     * @param target target string
     * @param n nth occurrence.
     * @return the index of nth occurrence of a string.
     */
    public static int ordinalIndexOf(String str, String target, int n) {
        int pos = str.indexOf(target);
        while (--n > 0 && pos != -1)
            pos = str.indexOf(target, pos + 1);
        return pos;
    }

    /**
     * Load the data from the file.
     * @return a list of tasks containing all the data.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            File data = new File(this.filePath);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                char type = task.charAt(0);
                char isDone = task.charAt(4);
                boolean status = isDone == '1' ? true : false;
                if (type == 'T') {
                    taskList.add(new Todo(task.substring(8), status));
                } else if (type == 'E') {
                    int pos = ordinalIndexOf(task, "|", 3);
                    String description = task.substring(8, pos);
                    String time = task.substring(pos + 1);
                    taskList.add(new Event(description, LocalDateTime.parse(time.substring(1), validFormat), status));
                } else if (type == 'D') {
                    int pos = ordinalIndexOf(task, "|", 3);
                    String description = task.substring(8, pos);
                    String by = task.substring(pos + 1);
                    taskList.add(new Deadline(description, LocalDateTime.parse(by.substring(1), validFormat), status));
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            File dir = new File("data");
            File file = new File("data/data.txt");
            if (!dir.exists()) {
                System.out.println("The folder is not created yet!");
                dir.mkdir();
                System.out.println("Folder created");
            } else if (!file.exists()) {
                System.out.println("No file is found at path: " + filePath);
                try {
                    file.createNewFile();
                    System.out.println("File created.");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                
            }
            
        }
        
        return taskList;
    }

    /**
     * Write a list of tasks into the file.
     * @param taskList a list of tasks.
     */
    public void writeData(ArrayList<Task> taskList) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                myWriter.write(task.toWrite() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
