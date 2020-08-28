import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {
    String filePath;
    ArrayList<Task> tasks = new ArrayList<Task>();

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage load() throws EmptyDescriptionException {
        createNewFile();
        decodeTxtFile();
        return this;
    }

    public void updateFile() {
        try {
            FileWriter fw = new FileWriter(this.filePath);

            for (Task task : tasks) {
                fw.write(task.encode() + System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewFile() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File duke = new File(this.filePath);
        if (!duke.exists()) {
            try {
                duke.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void decodeTxtFile() throws EmptyDescriptionException {
        File f = new File(this.filePath);
        try {
            Scanner s = new Scanner(f);

            String string;
            Task task = null;

            while (s.hasNext()) {
                string = s.nextLine();

                switch (string.charAt(0)) {
                    case 'D':
                        task = Deadline.decode(string);
                        break;
                    case 'E':
                        task = Event.decode(string);
                        break;
                    case 'T':
                        task = ToDo.decode(string);
                        break;
                }
                this.tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
