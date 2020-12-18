package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



/**
 * The Storage class handles the read and write of data from and to the local disk.
 */
public class Storage {
    public static final String STORAGE_INIT_MESSAGE =
            "Storage space for you tasks has been initialized successfully.";
    public static final String STORAGE_ERROR_MESSAGE =
            "Oops! Something went wrong when loading your tasks from storage.";

    /**
     * Data file path.
     */
    private final String filePath;

    /**
     * Instantiates a Storage object with a file path.
     *
     * @param path File path to read from and write to.
     */
    Storage(String path) {
        filePath = path;
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                if (f.getParentFile().mkdirs() && f.createNewFile()) {
                    System.out.println(STORAGE_INIT_MESSAGE);
                }
            }
        } catch (IOException e) {
            System.out.println(STORAGE_ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Reads saved data from the storage file and returns a list of {@link Task}s.
     *
     * @return A list of {@link Task}s.
     * @throws IOException Exception when reading from storage file.
     */
    public List<Task> processStorage() throws IOException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        List<Task> list = new ArrayList<>();
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] dataArray = data.split(" \\| ");
            switch (dataArray[1]) {
            case "T":
                list.add(new ToDo(dataArray[3], dataArray[2]));
                break;
            case "E":
                list.add(new Event(dataArray[3], LocalDate.parse(dataArray[4]), dataArray[2]));
                break;
            case "D":
                list.add(new Deadline(dataArray[3], LocalDate.parse(dataArray[4]), dataArray[2]));
                break;
            default:
                throw new IOException("Invalid data");
            }
        }
        return list;
    }

    /**
     * Writes a list of {@link Task}s to the storage file.
     *
     * @param list A list of {@link Task}s.
     * @throws IOException Exception when writing to storage file.
     */
    public void writeData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: list) {
            String toWrite = "";
            toWrite += (t.encode() + "\n");
            fw.write(toWrite);
        }
        fw.close();
    }
}
