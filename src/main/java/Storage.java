
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

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
            t.markAsDone();
        }
        return t;
    }

    /**
     * Overrides (if existing file exists) and saves a {@code TaskList} to the file in the filepath.
     *
     * @param p {@code TaskList} to be saved into file.
     */
    public void updateList(TaskList p) {

        try {
            FileWriter fw = new FileWriter(this.file.getPath());
            for (Task t : p.getList()) {
                fw.write(this.format(t) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves tasks (if present) from file in filepath and loads it into the {@code TaskList}.
     *
     * @param p {@code TaskList} that will be loaded with tasks from previous iterations if present.
     */
    public void loadList(TaskList p) {
        try {
            FileReader fr = new FileReader(this.file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String currLine = br.readLine();
            while (currLine != null) {
                p.addTask(this.reformat(currLine));
                currLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
