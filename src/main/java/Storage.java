import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * storage class in charge of loading and saving
 * list into file (/data/duke.txt)
 */
public class Storage {

    private String filePath;

    /**
     * constructor, intializes file path
     * @param filePath file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loads file text into array list of task
     * @return task list
     */
    ArrayList<Task> loadFile() {
        initFile();

        ArrayList<Task> ls = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            while (true) {
                String line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    //no more tasks
                    break;
                }
                Task task = Task.parseToTask(line);
                if (task != null) {
                    ls.add(task);
                }
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return ls;
    }

    /**
     * saves task list into file
     * @param ls task list
     */
    void saveFile(TaskList ls) {
        System.out.println("Saving...");
        File f = new File(this.filePath);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(ls.toString());
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Checks if the directory or file exists
     * and if not, creates one
     */
    void initFile() {
        File dir = new File ("data");
        //create dir if not there
        if (!dir.isDirectory()) {
            if (!dir.mkdir()) {
                System.out.println("Error creating folder");
            }
        }

        File f = new File(this.filePath);
        //create file if not there
        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    System.out.println("Error creating file");
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
