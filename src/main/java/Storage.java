
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The {@code Storage} class stores and retrieves tasks.
 */
public class Storage {

    private File file;

    /**
     * Initialises a Storage object by creating a new file or retrieving
     * an existing file if possible.
     */
    public Storage() {
        Path path = Paths.get("data/");
        try {
            Files.createDirectories(path);
            file = new File("data/list.txt");
            file.createNewFile();

            assert file.exists() : "file should exist";

        } catch (FileAlreadyExistsException e) {
            // the directory already exists.
        } catch (IOException e) {
            System.out.println("ioexception");
            // something else went wrong
        }

    }

    private String format(Task t) {
        String div = "-----";
        String timing = t.getTiming();
        if (t.getType().equals("T")) {
            timing = "NaN";
        }
        return t.getType() + div
            + t.getStatusIcon() + div
            + t.getDescription()
            + div + timing;
    }

    private Task reformat(String s) {
        String[] arr = s.split("-----");
        Task t = null;
        switch (arr[0]) {

        case "T":
            t = new Todo("todo " + arr[2]);
            break;
        case "E":
            t = new Event(arr[2], arr[3]);
            break;
        case "D":
            t = new Deadline(arr[2], arr[3]);
            break;
        default:
            System.out.println("Something went wrong here");
        }
        if (arr[1].equals("\u2713")) {

            assert t != null : "t should not be null";

            t.markAsDone();
        }
        return t;
    }

    /**
     * Overrides (if existing file exists) and saves a {@code TaskList} to the file in the filepath.
     *
     * @param taskList {@code TaskList} to be saved into file.
     */
    public void updateList(TaskList taskList) {

        try {
            FileWriter fileWriter = new FileWriter(this.file.getPath());
            writeTasksIntoList(taskList, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTasksIntoList(TaskList taskList, FileWriter writer) throws IOException {
        for (Task task : taskList.getList()) {
            writer.write(this.format(task) + "\n");
        }
    }

    /**
     * Retrieves tasks (if present) from file in filepath and loads it into the {@code TaskList}.
     *
     * @param taskList {@code TaskList} that will be loaded with tasks from previous iterations if present.
     */
    public void loadList(TaskList taskList) {
        try {
            FileReader fileReader = new FileReader(this.file.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            readFromFileThenAddToList(taskList, bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFileThenAddToList(TaskList taskList, BufferedReader reader) throws IOException {
        String currentLine = reader.readLine();
        while (currentLine != null) {
            taskList.addTask(reformat(currentLine));
            currentLine = reader.readLine();
        }
    }

}
