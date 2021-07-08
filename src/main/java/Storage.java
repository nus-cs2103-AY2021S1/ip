import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    Storage(String filePath) throws IOException {
        this.filePath = filePath;
        File f = new File(this.filePath);

        // Create /data folder if does not exist
        if (f.getParentFile().mkdirs()) {
            System.out.println("Folders created.");
        }

        // Create /data/duke.txt if does not exist
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        }
    }

    /**
     * Saves the list of tasks to disk.
     *
     * @param taskList - TaskList object to be saved to disk
     */
    void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : taskList.tasks) {
            fw.write(t.toFileFormat() + "\n");
        }
        fw.close();
    }

    /**
     * Loads the list of tasks from disk.
     *
     * @return TaskList object loaded from disk
     */
    ArrayList<Task> load() throws IOException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNext()) {
            String fileFormatString = s.nextLine();
            String[] fileTokens = fileFormatString.split("\\|");
            assert fileTokens.length > 1;
            switch (fileTokens[0]) {
                case "E":
                    taskList.add(Event.fromFileFormat(fileFormatString));
                    break;
                case "D":
                    taskList.add(Deadline.fromFileFormat(fileFormatString));
                    break;
                case "T":
                    taskList.add(Todo.fromFileFormat(fileFormatString));
                    break;
            }
        }
        return taskList;
    }
}
