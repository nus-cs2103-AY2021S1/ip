package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class handles the read and write of data from and to the local disk.
 */
public class Storage {
    /**
     * Data file path.
     */
    private final String FILE_PATH;

    /**
     * Instantiates a Storage object with a file path.
     * @param path File path to read from and write to.
     */
    Storage(String path) {
        FILE_PATH = path;
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                if (f.getParentFile().mkdirs() && f.createNewFile()) {
                    System.out.println("\tStorage space for you tasks has been initialized successfully.");
                }
            }
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when loading your tasks from storage.");
            e.printStackTrace();
        }
    }

    /**
     * Reads saved data from the storage file and returns a list of {@link Task}s.
     * @return A list of {@link Task}s.
     * @throws IOException Exception when reading from storage file.
     */
    List<Task> processStorage() throws IOException {
        File f = new File(FILE_PATH);
        Scanner sc = new Scanner(f);
        List<Task> list = new ArrayList<>();
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] dataArray = data.split(" \\| ");
            switch (dataArray[0]) {
                case "T":
                    list.add(new ToDo(dataArray[2], dataArray[1]));
                    break;
                case "E":
                    list.add(new Event(dataArray[2], LocalDate.parse(dataArray[3]), dataArray[1]));
                    break;
                case "D":
                    list.add(new Deadline(dataArray[2], LocalDate.parse(dataArray[3]), dataArray[1]));
                    break;
                default:
                    throw new IOException("Invalid data");
            }
        }
        return list;
    }

    /**
     * Writes a list of {@link Task}s to the storage file.
     * @param list A list of {@link Task}s.
     * @throws IOException Exception when writing to storage file.
     */
    public void writeData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t: list) {
            String toWrite = "";
            toWrite += (t.encode() + "\n");
            fw.write(toWrite);
        }
        fw.close();
    }
}
