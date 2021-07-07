package Duke;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Exception.IncorrectInputException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

public class Storage {

    private File file;
    private Path filepath;

    /**
     * Constructor for Duke.Storage.
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = Paths.get(filepath);
        this.file = this.filepath.toAbsolutePath().toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Constructor for Duke.Storage
     */
    public Storage() {
        try {
            String currentDirectory = System.getProperty("user.dir") + "/tasks";
            File parent = new File(currentDirectory);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            this.file = new File(parent + "/tasklist.txt");
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all the tasks from the files.
     * @return an arraylist of all the tasks
     * @throws FileNotFoundException
     * @throws IncorrectInputException
     */
    public ArrayList<Task> loadData() throws FileNotFoundException, IncorrectInputException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            ArrayList<Task> list = new ArrayList<>();
            String data;
            reader.readLine();
            while ((data = reader.readLine()) != null) {
                boolean isDone = data.contains("| 1 |") ? true : false;
                String[] arr = data.split(" \\| ", 4);
                String description = arr[2];
                if (data.startsWith("T")) {
                    ToDo toDo = new ToDo(description);
                    if (isDone) {
                        toDo.markAsDone();
                    }
                    list.add(toDo);
                } else if (data.startsWith("D")) {
                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(arr[3], outputFormat);
                    dateTime.format(inputFormat);
                    Deadline deadline = new Deadline(description, dateTime);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                } else if (data.startsWith("E")) {
                    Event event = new Event(description, arr[3]);
                    if (isDone) {
                        event.markAsDone();
                    }
                    list.add(event);
                }
            }
            return list;
        } catch (Exception e) {
            return new ArrayList<Task>();
        }
    }

    /**
     * Save new task to the file.
     * @param task
     * @throws IOException
     */
    public void saveTask(Task task) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter(this.file.getAbsolutePath(), true));
        file.newLine();
        file.write(task.toSave());
        file.close();
    }
}
