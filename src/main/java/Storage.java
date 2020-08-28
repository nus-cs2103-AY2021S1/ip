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
    ArrayList<String> encodedTasks = new ArrayList<>();

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws EmptyDescriptionException {
        createFile();
        return decodeTxtFile();
    }

    public void save(TaskList taskList) {

        try {
            ArrayList<String> encodedTasks = new ArrayList<>();
            FileWriter fw = new FileWriter(this.filePath);

            for (Task task : taskList.getTaskList()) {
                String encodedTask = task.encode();
                encodedTasks.add(encodedTask);
                fw.write(encodedTask + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
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

    public ArrayList<Task> decodeTxtFile() throws EmptyDescriptionException {
        File f = new File(this.filePath);
        ArrayList<Task> decodedTasks = new ArrayList<>();

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
                decodedTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return decodedTasks;
    }
}
